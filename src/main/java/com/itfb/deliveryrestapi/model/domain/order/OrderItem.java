package com.itfb.deliveryrestapi.model.domain.order;

import com.itfb.deliveryrestapi.model.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    public OrderItem() {
    }

    public OrderItem(Product productId, int quantity, Order orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
