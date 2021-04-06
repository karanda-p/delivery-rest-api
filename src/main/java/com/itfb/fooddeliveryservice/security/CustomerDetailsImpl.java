package com.itfb.fooddeliveryservice.security;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@ToString
public class CustomerDetailsImpl implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private boolean enabled;
    private final Collection<GrantedAuthority> authorities = new HashSet<>();
    private String phone;

    public CustomerDetailsImpl(Long id, String username, String password, boolean enabled) {
        this.id = id;
        this.login = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }


    public CustomerDetailsImpl(Customer customer) {
        this.id = customer.getId();
        this.login = customer.getLogin();
        this.password = customer.getPassword();
        this.enabled = customer.isEnabled();
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public CustomerDetailsImpl(Long id, String username, String password, boolean enabled, Collection<GrantedAuthority> authorities, String phone) {
        this.id = id;
        this.login = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities.add(new SimpleGrantedAuthority("USER"));
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

}
