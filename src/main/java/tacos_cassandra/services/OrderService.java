package tacos_cassandra.services;

import tacos_cassandra.entities.TacoOrder;

public interface OrderService {
    TacoOrder saveOrder(TacoOrder tacoOrder);
}
