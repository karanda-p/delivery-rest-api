package com.itfb.fooddeliveryservice.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.ldap")
public class LdapProperties {

    private String urls;
    private String base;
    private String username;
    private String password;
    private String userSearchFilter;
    private String userUid;
    private String referral;
    private String[] attrs;

}
