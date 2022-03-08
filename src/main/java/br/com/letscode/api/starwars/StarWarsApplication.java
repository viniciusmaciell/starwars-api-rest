package br.com.letscode.api.starwars;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarWarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApplication.class, args);

        Location location = new Location(50, 45, "Betelgeuse");

        Rebel rebel = new Rebel("Luke", 24, GenderEnum.MALE, location);

        System.out.println(rebel);
    }

}
