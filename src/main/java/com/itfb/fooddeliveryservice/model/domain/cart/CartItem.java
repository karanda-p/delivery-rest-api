package com.itfb.fooddeliveryservice.model.domain.cart;

import com.itfb.fooddeliveryservice.model.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {

    @Id
    @SequenceGenerator(sequenceName = "cart_item_seq", name = "cart_item_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private Long cartId;

    private double amount;

    public CartItem() {
    }

    public CartItem(Long productId, int quantity, Long cartId) {
        this.productId = productId;
        this.quantity = quantity;
        this.cartId = cartId;
    }
}
