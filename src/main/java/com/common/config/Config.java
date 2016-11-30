package com.common.config;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.Properties;
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"com.common","com.common.service.workingThread"})   //, "com.common.dao.entity", "com.common.dao.insert.DAOInsertThread", "com.common.service","com.common.service.logger", "com.common.service.workingThread"
@EnableTransactionManagement
@PropertySource(value = "classpath:util.properties")
public class Config {
    Logger logger = Logger.getLogger(String.valueOf(Config.class));

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory listenerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue() {
        return new Queue("json");
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("getData");
    }

    @Bean//create queue rabbitMQ
    public Queue myQueue2() {
        return new Queue("insertStat");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean(name="pathToSave")                        //upload CSV file
    public String getPath(){
     return this.path;
    }
    @Value("${pathToSave}")
    private String path;
    @Value("${jdbc.hsqldb.postgres.driverClass}")
    private String driverClass;
    @Value("${jdbc.hsqldb.postgres.url}")
    private String jdbcUrl;
    @Value("${jdbc.hsqldb.postgres.username}")
    private String jdbcUserName;
    @Value("${jdbc.hsqldb.postgres.password}")
    private String jdbcPassword;

//    @Value("${jdbc.hsqldb.driverClass}")
//    private String driverClass;
//    @Value("${jdbc.hsqldb.url}")
//    private String jdbcUrl;
//    @Value("${jdbc.hsqldb.username}")
//    private String jdbcUserName;
//    @Value("${jdbc.hsqldb.password}")
//    private String jdbcPassword;


    @Value("classpath:dbschema.sql")
    private Resource dbschemaSqlScript;
    @Value("classpath:test-data.sql")
    private Resource testDataSqlScript;

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(getDriverManagerDataSource());
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {              //DB scripts
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbschemaSqlScript);
        populator.addScript(testDataSqlScript);
        return populator;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(new String[]{"com.common.dao", "com.common.dao.entity"});
        em.setDataSource(getDriverManagerDataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) vendorAdapter).setGenerateDdl(true);
        ((HibernateJpaVendorAdapter) vendorAdapter).setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect",
               "org.hibernate.dialect.PostgreSQLDialect");
//                "PostgreSQL94JsonDialect");

//        jpaProperties.put("hibernate.dialect",
//                "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", "false");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
       // jpaProperties.put("hibernate.jdbc.use_streams_for_binary", "true");
        em.setJpaProperties(jpaProperties);
        return em;
    }


    @Bean(name = "jpaTransactionManager")
    public JpaTransactionManager getJpaTransactionManager() {
        JpaTransactionManager jpa = new JpaTransactionManager();
        jpa.setEntityManagerFactory(getLocalContainerEntityManagerFactoryBean()
                .getNativeEntityManagerFactory());
        return jpa;
    }
//
//    @Bean(name = "properties")
//    @Transactional
//    public int getProperties(SessionFactory sessionFactory) {
//        Session session;
//        try {
//            session = sessionFactory.getCurrentSession();
//        } catch (HibernateException e) {
//            session = sessionFactory.openSession();
//        }
//        Criteria criteria = sessionFactory
//                .getCurrentSession()
//                .createCriteria(User.class, "arr");
//        User a = new User();
//        a = (User) criteria.list().get(0);
//        System.out.println("configure   " + a.getUsername() + "   " + a.getPassword());
//        return 8;
//    }

    //@Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DriverManagerDataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(     //entities
                dataSource);
        sessionBuilder.scanPackages("com.common.dao.entity.user");
        sessionBuilder.scanPackages("com.common.dao.entity");
        sessionBuilder.scanPackages("com.common.dao.entity.message");
        sessionBuilder.scanPackages("com.common.dao.entity.company");
        sessionBuilder.scanPackages("com.common.dao.entity.task");
        sessionBuilder.scanPackages("com.common.service.workingThread");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setMaxUploadSize(200000000);                                //max file size
        return resolver;
    }

//    @Bean(name="ControllerLogger")
//    public ControllerLogger logger(){
//        return new ControllerLogger();
//    }
//
//    @Bean(name="PoincutDefinition")
//    public PointcutDefinition pointcut(){
//        return new PointcutDefinition();
//    }
}
