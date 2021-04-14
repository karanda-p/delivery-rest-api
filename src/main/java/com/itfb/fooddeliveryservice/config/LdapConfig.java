package com.itfb.fooddeliveryservice.config;

import com.itfb.fooddeliveryservice.component.LdapProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@RequiredArgsConstructor
public class LdapConfig {

    private final LdapProperties ldapProperties;

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setBase(ldapProperties.getBase());
        contextSource.setUrl(ldapProperties.getUrls());
        contextSource.setUserDn(ldapProperties.getUsername());
        contextSource.setPassword(ldapProperties.getPassword());

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(LdapContextSource ldapContextSource) {
        return new LdapTemplate(ldapContextSource);
    }
}
