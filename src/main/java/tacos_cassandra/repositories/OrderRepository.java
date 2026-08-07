package tacos_cassandra.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos_cassandra.entities.TacoOrder;

import java.util.UUID;

public interface OrderRepository
        extends CassandraRepository<TacoOrder, UUID> {
    //   CRUD (Create, Read, Up- date, Delete – создать, прочитать, изменить, удалить)
    // TacoOrder save(TacoOrder order);

    //Методы репозитория формируются из
// глагола, необязательного подлежащего, слова By и предиката.
    //   List<TacoOrder> findByDeliveryZip(String deliveryZip);
//find (найти), read (прочитать) и get (получить) как синонимы, count - чтобы вернул целое число, подсчитал
//    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
    //           String deliveryZip, Date startDate, Date endDate);


//List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);
    //@Query("Order o where o.deliveryCity=’Seattle’")
    // List<TacoOrder> readOrdersDeliveredInSeattle();
}

