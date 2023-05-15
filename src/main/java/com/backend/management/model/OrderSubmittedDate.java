package com.backend.management.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_submitted_date")
public class OrderSubmittedDate implements Serializable {
    @EmbeddedId
    private OrderSubmittedDateKey id;
    @MapsId("order_id")
    @ManyToOne
    private Order order;

    public OrderSubmittedDate() {
    }
    public OrderSubmittedDate(OrderSubmittedDateKey id, Order order) {
        this.id = id;
        this.order = order;
    }


    public OrderSubmittedDateKey getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }
}
