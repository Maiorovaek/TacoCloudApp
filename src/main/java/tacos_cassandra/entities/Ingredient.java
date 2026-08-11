package tacos_cassandra.entities;



import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data

//@Data неявно добавляет конструктор с обязательными аргументами, но,
// когда используется @NoArgsConstructor, этот конструктор удаляется
@AllArgsConstructor//упростить создание объекта Ingredient со всеми инициализированными свойствам

//создание конструктора без аргументов,
// force=true сделать все свойства финальными c значениями null, 0 или false
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
//Ingredient помеченая @Entity - сущность JPA.
// Имя таблицы по умолчанию - Ingredient
//@Entity
@Table("ingredients")
public class Ingredient
{
    @PrimaryKey
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }


}
