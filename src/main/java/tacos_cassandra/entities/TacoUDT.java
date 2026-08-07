package tacos_cassandra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@NoArgsConstructor  // Обязательно: пустой конструктор для Cassandra
@AllArgsConstructor // Генерирует конструктор TacoUDT(String name, List<IngredientUDT> ingredientudtlist)
@UserDefinedType("taco")
public class TacoUDT {

    private String name;
    @Column("ingredientudtlist")
    @Frozen // Для вложенных коллекций в Cassandra это обязательно
    private List<IngredientUDT> ingredientudtlist;
}
