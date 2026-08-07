package tacos_cassandra.services;

import org.springframework.stereotype.Service;
import tacos_cassandra.entities.Ingredient;
import tacos_cassandra.repositories.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        var ingredients = ingredientRepository.findAll();
        var ingredientsList = new ArrayList<Ingredient>();

        ingredients.forEach(ingredientsList::add);

        return ingredientsList;
    }

    @Override
    public Optional<Ingredient> findIngredientById(String id) {
        return ingredientRepository.findById(id);
    }


    //    @Override
//    public Iterable<Ingredient> findAll() {
//        return
//                jdbcTemplate.query(
//                        "select id, name, type from Ingredient",
//                        this::mapRowToIngredient);
//
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
//            throws SQLException {
//        return new Ingredient(
//                row.getString("id"),
//                row.getString("name"),
//                Ingredient.Type.valueOf(row.getString("type")));
//    }
}
