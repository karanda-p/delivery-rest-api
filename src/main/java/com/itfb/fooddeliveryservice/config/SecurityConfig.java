package com.itfb.fooddeliveryservice.config;

import com.itfb.fooddeliveryservice.component.LdapProperties;
import com.itfb.fooddeliveryservice.security.UserDetailsServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.server.UnboundIdContainer;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LdapProperties ldapProperties;
    private final LdapContextSource ldapContextSource;
    private final UserDetailsServiceImpl userDetailsService;
    @Qualifier("ldapUserDetailsContextMapper")
    @NonNull
    private final UserDetailsContextMapper ldapUserDetailsContextMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.ldapAuthentication()
                .contextSource(ldapContextSource)
                .userSearchFilter(ldapProperties.getUserSearchFilter())
                .userDetailsContextMapper(ldapUserDetailsContextMapper);
//                .userSearchFilter("(sAMAccountName={0})")
//                .userSearchBase("CN=Users")
//                .contextSource()
//                .url("ldap://192.168.30.10:3268/DC=itfbgroup,DC=local")
//                .managerDn("cn=portal_user,cn=Users,dc=itfbgroup,dc=local")
//                .managerPassword("Q1w2e3r4");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/customers").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout().permitAll();
    }

}
