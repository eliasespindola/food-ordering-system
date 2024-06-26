package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.service.domain.valueobject.PaymentId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {
    private final OrderId orderId;
    private final CustomerId customerId;
    private final Money price;
    private PaymentStatus status;
    private ZonedDateTime createdAt;

    public void initializePayment() {
        setId(new PaymentId(UUID.randomUUID()));
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void validatePayment(List<String> failureMessage) {
        if (price == null || !price.isGreaterThanZero()) {
            failureMessage.add("Total price must be greater than zero!");
        }
    }

    public void updateStatus(PaymentStatus status) {
        this.status = status;
    }


    private Payment(Builder builder) {
        super.setId(builder.paymentId);
        orderId = builder.orderId;
        customerId = builder.customerId;
        price = builder.price;
        status = builder.status;
        createdAt = builder.createdAt;
    }


    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getPrice() {
        return price;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private PaymentId paymentId;
        private final OrderId orderId;
        private final CustomerId customerId;
        private final Money price;
        private PaymentStatus status;
        private ZonedDateTime createdAt;

        public Builder(OrderId orderId, CustomerId customerId, Money price) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.price = price;
        }

        public Builder id(PaymentId val) {
            paymentId = val;
            return this;
        }

        public Builder status(PaymentStatus val) {
            status = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
