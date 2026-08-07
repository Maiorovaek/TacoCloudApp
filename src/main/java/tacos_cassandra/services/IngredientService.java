package tacos_cassandra.services;

import tacos_cassandra.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> findAllIngredients();

    Optional<Ingredient> findIngredientById(String id);
}





