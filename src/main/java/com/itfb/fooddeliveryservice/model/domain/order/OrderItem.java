package com.itfb.fooddeliveryservice.model.domain.order;

import com.itfb.fooddeliveryservice.model.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @SequenceGenerator(sequenceName = "order_item_seq", name = "order_item_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_gen")
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
