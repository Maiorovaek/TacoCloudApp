package tacos_cassandra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos_cassandra.entities.Ingredient;
import tacos_cassandra.repositories.IngredientRepository;

@SpringBootApplication
//1. @SpringBootConfiguration  -определяет этот класс как класс конфигурации,
//2. EnableAutoConfiguration- эта аннотация сообщает Spring Boot о необходимости автоматически настраивать
// любые компоненты, которые могут вам понадобиться ,
//3. @ComponentScan - включает сканирование компонентов. Меха- низм сканирования позволяет объявлять другие
// классы с анно- тациями, такими как @Component, @Controller и @Service, чтобы фреймворк Spring
// автоматически обнаруживал и регистрировал их как компоненты в контексте приложения Spring.
public class TacoCloudApplicationJpa implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplicationJpa.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return args -> {
            // Очищаем и записываем заново (или проверяем repo.count() == 0)
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }

    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
