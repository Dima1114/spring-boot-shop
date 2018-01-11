package com.freeride.shop.config;

import com.freeride.shop.interseptors.CategoryInterceptor;
import com.freeride.shop.interseptors.ItemsInterceptor;
import com.freeride.shop.interseptors.SearchInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationContext.getBean(ItemsInterceptor.class))
                .addPathPatterns("/", "/items/*", "/items/show/*", "/items/all/search", "/admin/**")
                .excludePathPatterns("/admin", "/admin/users/**");
        registry.addInterceptor(applicationContext.getBean(SearchInterceptor.class)).addPathPatterns("/items/all/search");
        registry.addInterceptor(applicationContext.getBean(CategoryInterceptor.class))
                .addPathPatterns("/admin/categories/**");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setCacheSeconds(0);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
