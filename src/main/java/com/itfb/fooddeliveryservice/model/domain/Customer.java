package com.itfb.fooddeliveryservice.model.domain;

import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @SequenceGenerator(sequenceName = "customer_seq", name = "customer_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_gen")
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone", unique = true)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private Long cartId;

    @Column(name = "enabled")
    private boolean enabled;

//    @Column(name = "authorities")
//    private Collection<GrantedAuthority> authorities;

    public Customer() {
    }

    public Customer(String login, String password, String phone) {
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}
