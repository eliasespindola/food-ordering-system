package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/***
 * Esta no common pq pode ser utilizada em outras entidades e modulos
 */
public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}
