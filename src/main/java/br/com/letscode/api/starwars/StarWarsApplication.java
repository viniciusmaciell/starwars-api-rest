package br.com.letscode.api.starwars;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.LocationModel;
import br.com.letscode.api.starwars.models.RebelModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarWarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApplication.class, args);

        LocationModel location = new LocationModel(50, 45, "Betelgeuse");

        RebelModel rebel = new RebelModel("Luke", 24, GenderEnum.MALE, location);

        System.out.println(rebel);
    }

}
