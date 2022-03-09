package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RebelRepository {

    private static List<Rebel> rebels = new ArrayList<>();

//    static {
//
//        rebels.addAll(Arrays.asList(new Rebel("Chuew",
//                45,
//                GenderEnum.MALE,
//                new Location(-50, 45, "bEt")
//        ), new Rebel("Chue2",
//                45,
//                GenderEnum.MALE,
//                new Location(-50, 45, "bEt")
//        )));
//    }

    public Rebel save(Rebel rebel){
        rebel.setId(UUID.randomUUID());
        rebels.add(rebel);
        return rebel;
    }

    public List<Rebel> getAll() {
        return rebels;
    }

//    public Rebel findById(UUID id){
//        System.out.println("repository findById");
//        System.out.println("id: " + id);
//        Rebel returnedRebel = rebels.stream()
//                .filter(rebel -> rebel.getId().equals(id))
//                .findFirst().get();
//        System.out.println(returnedRebel);
//        return returnedRebel;
//    }

    public Rebel updateRebelLocation(UUID id, Location location) {
        Rebel rebelToReturn = new Rebel();
        for (Rebel rebel : rebels){
            if (rebel.getId().equals(id)){
                rebel.setLocation(location);
                rebelToReturn = rebel;
                break;
            }
        }

        return rebelToReturn;
    }
}

