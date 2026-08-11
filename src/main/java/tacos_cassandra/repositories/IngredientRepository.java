package tacos_cassandra.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos_cassandra.entities.Ingredient;

public interface IngredientRepository
        extends CassandraRepository<Ingredient, String> {

}
