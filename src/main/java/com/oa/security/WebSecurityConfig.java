package com.oa.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by HOZANDUNG on 17/5/8.
 */
@Configuration
@EnableWebSecurity//禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用Security注解，例如最常用的@PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] URLS = {"/**"};

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }


    //身份验证配置，用于注入自定义身份验证Bean和密码校验规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    //configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(URLS).permitAll()
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers(URLS)
                .and().formLogin().loginPage("/login_page").permitAll().defaultSuccessUrl("/home",true).failureUrl("/incorrect_page")
                .and().logout().logoutUrl("/logout");
    }

    //Web层面的配置，一般用来配置无需安全检查的路径
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/fonts/**", "/sass/**", "/**/favicon.ico");
    }
}

