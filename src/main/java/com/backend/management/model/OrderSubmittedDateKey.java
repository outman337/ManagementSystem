package com.backend.management.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class OrderSubmittedDateKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long order_id;
    private LocalDate date;

    public OrderSubmittedDateKey(){};

    public OrderSubmittedDateKey(Long order_id, LocalDate date) {
        this.order_id = order_id;
        this.date = date;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public OrderSubmittedDateKey setOrder_id(Long order_id) {
        this.order_id = order_id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderSubmittedDateKey setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSubmittedDateKey that = (OrderSubmittedDateKey) o;
        return order_id.equals(that.order_id) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, date);
    }
}
