package tacos_cassandra.entities;


import com.datastax.oss.driver.api.core.uuid.Uuids;
//import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
@Table("tacos") //Хранить в таблице "tacos"

public class Taco {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    //Определение ключа раздела
    //Он указывает, что свойство id играет роль ключа раздела, используемого для выбора
    // раздела или разделов, где Cassandra будет хранить записи с экземплярами Taco.
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();


    //Определение ключа кластеризации- служит вторым столбцом первичного ключа
    //ключи кластеризации используют- ся для определения порядка хранения записей в разделе
    //здесь в порядке убывания, поэтому в пределах данного раздела
    // более новые записи будут помещаться в начало таблицы tacos.
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "name must be at least 5 characters long")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "taco_order")
//    private TacoOrder tacoOrder;
//
//    @NotNull
//    @Size(min = 1, message = "You must choose at least 1 ingredient")
    //заимосвязь между Taco и списком ингредиентов Ingredient
    //Объект Taco может включать в список несколько объектов Ingredient,
    // а один объект Ingredient может быть частью списков в нескольких объектах Taco.
//    @ManyToMany
//    @JoinTable(
//            name = "taco_ingredients",
//            joinColumns = @JoinColumn(name = "taco_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
//    )


    @Size(min = 1, message = "You must choose at least 1 ingredient")
    //   Отображает список в столбец "ingredients"
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        //this.ingredients.add(ingredient);

        /// столбцы, хранящие коллекции данных, такие как столбец ingredients, должны быть коллекциями
        /// встроенных типов (целых чисел, строк и т. д.) или типов, определяемых пользователем.
        //Использовать класс Ingredient в качестве типа, определяемого пользователем, нельзя,
        // потому что аннотация @Table уже отобразила его как хранимую сущность в Cassandra.
        // Поэтому нам пришлось объявить новый класс, чтобы определить, как ингредиенты
        // будут храниться в столбце ingredients таблицы tacos.
        // IngredientUDT (где UDT означает User-Defined Type – определяемый пользователем тип) – это класс,
        // созданный специально для данной цели:
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}
