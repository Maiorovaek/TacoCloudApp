package tacos_cassandra.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos_cassandra.entities.*;
import tacos_cassandra.entities.Ingredient.Type;
import tacos_cassandra.services.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
//объект TacoOrder, должен поддерживаться на уровне сеанса.
// Это важно, потому что создание тако также является первым шагом в создании заказа, и
// созданный нами заказ необходимо будет перенести в сеанс, охватывающий несколько запросов.

public class DesignTacoController {

    private final IngredientService ingredientService;

    public DesignTacoController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientService.findAllIngredients();
        System.out.println("DEBUG: Всего в базе найдено: " + ingredients.size());
        Type[] types = Type.values();

        for (Type type : types) {
            List<Ingredient> filtered = (List<Ingredient>) filterByType(ingredients, type);
            System.out.println("Type: " + type + ", Count: " + filtered.size());
            model.addAttribute(type.toString().toLowerCase(),
                    filtered);
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        // 2. Логируем оригинальный Taco, чтобы увидеть пришедшие ингредиенты
        System.out.println("DEBUG: Пришедший тако с формы: " + taco.getName());
        System.out.println("DEBUG: Ингредиенты в форме тако: " + taco.getIngredients());

        TacoUDT tacoUDT = TacoUDRUtils.toTacoUDT(taco);
        System.out.println("DEBUG: Сконвертированный TacoUDT список: " + tacoUDT.getIngredientudtlist());

        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing taco: {}", tacoUDT);
        tacoOrder.addTaco(tacoUDT);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type
    ) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
