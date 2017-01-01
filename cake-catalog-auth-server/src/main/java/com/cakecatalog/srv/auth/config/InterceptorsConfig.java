package com.cakecatalog.srv.auth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@EnableWebMvc
public class InterceptorsConfig extends WebMvcConfigurerAdapter {

  @Bean
  public LoggingInterceptor loggingInterceptor() {
    return new LoggingInterceptor();
  }

  @Bean
  PortalUserEventHandler portalUserEventHandler() {
    return new PortalUserEventHandler();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor()).addPathPatterns("/**");
  }


  @Bean
  public FilterRegistrationBean someFilterRegistration() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(loggingFilter());
    registration.addUrlPatterns("/*");
    registration.addUrlPatterns("/**");
    registration.setName("loggingFilter");
    registration.setOrder(1);
    return registration;
  }

  @Bean(name = "loggingFilter")
  public Filter loggingFilter() {
    return new Filter() {
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
      }

      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
          LoggingInterceptor.updateMdcFromServletRequest((HttpServletRequest) request);
        }

        chain.doFilter(request, response);
      }

      @Override
      public void destroy() {
      }
    };
  }
}