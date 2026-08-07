package tacos_cassandra.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos_cassandra.entities.Ingredient;
import tacos_cassandra.entities.IngredientUDT;
import tacos_cassandra.repositories.IngredientRepository;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientUDT convert(String id) {
        //return ingredientRepo.findById(id).orElse(null);
        Optional<Ingredient> ingredient = ingredientRepo.findById(id);
        if (ingredient.isEmpty()) {
            return null;
        }

        return ingredient.map(i -> {
            return new IngredientUDT(i.getName(), i.getType());
        }).get();
    }

}
