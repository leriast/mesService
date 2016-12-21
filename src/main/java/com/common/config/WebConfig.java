package com.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.common")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/view/**/*").addResourceLocations("/view/")/*.addResourceLocations("/view/js/").addResourceLocations("/view/js/angularJs*//**").addResourceLocations("/css*//**").addResourceLocations("/css/tld")*/
                /*.addResourceLocations("/js/angularJs/controller/")
                .addResourceLocations("/js/bootstrap")
                .addResourceLocations("/js/angularJs/controller*//**")
                .addResourceLocations("/js/bootstrap*//**")*/;
        registry.addResourceHandler("/WEB-INF/view/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/WEB-INF/view/js/angular/**").addResourceLocations("/angular/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
    }


    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

}


