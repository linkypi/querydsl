package com.querydsl.apt.domain;

import static org.junit.Assert.assertEquals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDateTime;
import org.junit.Test;

public class Properties3Test {

    @Entity
    public static class Order {

        @Column(name = "order_date")
        @Temporal(TemporalType.TIMESTAMP)
        private java.util.Date orderDate;

        public LocalDateTime getOrderDate() {
            return orderDate != null ? new LocalDateTime(orderDate) : null;
        }
    }

    @Test
    public void PropertyType() {
        assertEquals(java.util.Date.class, QProperties3Test_Order.order.orderDate.getType());
    }

}
