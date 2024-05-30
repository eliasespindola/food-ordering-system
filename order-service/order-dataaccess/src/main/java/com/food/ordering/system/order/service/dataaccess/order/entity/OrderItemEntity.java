package com.food.ordering.system.order.service.dataaccess.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * desta entidade com os campos id e orderId juntos.
 * O campo de ID longo fornecerá apenas um item exclusivo em um pedido específico.
 * Portanto, para essa chave primária de várias colunas, criarei uma nova classe OrderItemEntityId.
 * Portanto, esta anotação IdClass é necessária para usar uma classe Id em uma entidade com chave primária de várias colunas.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderItemEntityId.class)
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    private Long id;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;
    private UUID productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}
