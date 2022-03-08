package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.LocationModel;
import br.com.letscode.api.starwars.models.RebelModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RebelRepository {

    private static List<RebelModel> rebels = new ArrayList<>();

    static {

        rebels.addAll(Arrays.asList(new RebelModel("Chuew",
                45,
                GenderEnum.MALE,
                new LocationModel(-50, 45, "bEt")
        ), new RebelModel("Chue2",
                45,
                GenderEnum.MALE,
                new LocationModel(-50, 45, "bEt")
        )));
    }

    public RebelModel cadastrar(RebelModel rebel){
        System.out.println("repository cadastrar");
        rebel.setId((long) (rebels.size() + 1));
        rebels.add(rebel);
        System.out.println(rebels.size());
        return rebel;
    }

    public List<RebelModel> getAll() {
        return rebels;
    }

    public RebelModel findById(Long id){
        System.out.println("repository findById");
        System.out.println("id: " + id);
        RebelModel returnedRebel = rebels.stream()
                .filter(rebel -> rebel.getId().equals(id))
                .findFirst().get();
        System.out.println(returnedRebel);
        return returnedRebel;
    }
}

