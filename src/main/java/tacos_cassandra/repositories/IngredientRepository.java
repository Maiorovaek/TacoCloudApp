package tacos_cassandra.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos_cassandra.entities.Ingredient;

public interface IngredientRepository
        extends CassandraRepository<Ingredient, String> {
////CRUD (Create, Read, Up- date, Delete – создать, прочитать, изменить, удалить)
//    Iterable<Ingredient> findAll();
//    Optional<Ingredient> findById(String id); - который необходим внутри IngredientByIdConverter
//    для поиска ингредиента по ID из чекбокса.

//    Ingredient save(Ingredient ingredient);
}
