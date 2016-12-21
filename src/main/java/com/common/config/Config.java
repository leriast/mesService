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
                new CachingConnectionFactory("localhost");     //where rabbit queue will be locate
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

    @Value("classpath:dbschema.sql")
    private Resource dbschemaSqlScript;
    @Value("classpath:test-data.sql")
    private Resource testDataSqlScript;

    @Value("${loggerHost}")
    private String loggerHost;

    @Value("${skype_login}")
    private String skype_login;

    @Value("${skype_password}")
    private String skype_password;

    @Value("${loggerPort}")
    private String loggerPort;

    @Value("${max_file_size}")
    private String max_file_size;

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
        em.setPackagesToScan(new String[]{"com.common.dao.entity.usertype","com.common.dao", "com.common.dao.entity"});
        em.setDataSource(getDriverManagerDataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) vendorAdapter).setGenerateDdl(true);
        ((HibernateJpaVendorAdapter) vendorAdapter).setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect",
               "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", "false");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.jdbc.use_streams_for_binary", "true");
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
        sessionBuilder.scanPackages("com.common.dao.entity.usertype");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = "filterMultipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();

        resolver.setMaxUploadSize(Integer.parseInt(max_file_size));                                //max file size
        return resolver;
    }

    @Bean(name = "loggerHost")
    public String getLoggerHost(){
        return this.loggerHost;
    }

    @Bean(name = "loggerPort")
    public int getLoggerPort(){
        return Integer.parseInt(this.loggerPort);
    }

    @Bean(name = "skypeLogin")
    public String getSkype_login(){
        return this.skype_login;
    }

    @Bean(name = "skypePassword")
    public String getSkype_password(){
        return this.skype_password;
    }


}
