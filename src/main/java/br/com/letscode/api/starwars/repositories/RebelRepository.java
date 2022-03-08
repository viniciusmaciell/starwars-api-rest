package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RebelRepository {

    private static List<Rebel> rebels = new ArrayList<>();

    static {

        rebels.addAll(Arrays.asList(new Rebel("Chuew",
                45,
                GenderEnum.MALE,
                new Location(-50, 45, "bEt")
        ), new Rebel("Chue2",
                45,
                GenderEnum.MALE,
                new Location(-50, 45, "bEt")
        )));
    }

    public Rebel cadastrar(Rebel rebel){
        System.out.println("repository cadastrar");
        rebel.setId((long) (rebels.size() + 1));
        rebels.add(rebel);
        System.out.println(rebels.size());
        return rebel;
    }

    public List<Rebel> getAll() {
        return rebels;
    }

    public Rebel findById(Long id){
        System.out.println("repository findById");
        System.out.println("id: " + id);
        Rebel returnedRebel = rebels.stream()
                .filter(rebel -> rebel.getId().equals(id))
                .findFirst().get();
        System.out.println(returnedRebel);
        return returnedRebel;
    }
}

