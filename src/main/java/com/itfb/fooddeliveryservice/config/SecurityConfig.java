package com.itfb.fooddeliveryservice.config;

import com.itfb.fooddeliveryservice.component.LdapProperties;
import com.itfb.fooddeliveryservice.component.UserDetailsAuthProperties;
import com.itfb.fooddeliveryservice.security.UserDetailsServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com .itfb.fooddeliveryservice.security.UserDetailsServiceImpl;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LdapContextSource ldapContextSource;
    private final LdapProperties ldapProperties;
    private final UserDetailsAuthProperties userDetailsAuthProperties;
    private final UserDetailsServiceImpl userDetailsService;
    @NonNull private final UserDetailsContextMapper ldapUserDetailsContextMapper;

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
        if (userDetailsAuthProperties.isEnabled()){
            auth.authenticationProvider(authenticationProvider());
        } else {
            auth.ldapAuthentication()
                    .contextSource(ldapContextSource)
                    .userSearchFilter(ldapProperties.getUserSearchFilter())
                    .rolePrefix("")
                    .withObjectPostProcessor(new ObjectPostProcessor<BindAuthenticator>() {

                        @Override
                        public BindAuthenticator postProcess(BindAuthenticator object) {
                            final FilterBasedLdapUserSearch ldapUserSearch =
                                    new FilterBasedLdapUserSearch("", ldapProperties.getUserSearchFilter()
                                            , ldapContextSource);
                            ldapUserSearch.setReturningAttributes(ldapProperties.getAttrs());
                            object.setUserSearch(ldapUserSearch);
                            return object;
                        }
                    })
                    .userDetailsContextMapper(ldapUserDetailsContextMapper);
        }
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
