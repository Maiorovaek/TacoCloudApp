package tacos_cassandra.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos_cassandra.entities.TacoOrder;

import java.util.UUID;

public interface OrderRepository
        extends CassandraRepository<TacoOrder, UUID> {

}

