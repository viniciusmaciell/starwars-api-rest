package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.dtos.ReportDto;
import br.com.letscode.api.starwars.models.Exchange;
import br.com.letscode.api.starwars.models.Item;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class RebelRepository {

    private static List<Rebel> rebels = new ArrayList<>();

    private static List<Rebel> traidorsRebels = new ArrayList<>();

    private static List<Exchange> openOffers = new ArrayList<>();

    static {
        openOffers.addAll(
                Arrays.asList(
                        new Exchange(1L,
                                Arrays.asList(new Item(1L, "arma", Integer.valueOf("1")),
                                            new Item(2L, "municao", Integer.valueOf("2"))),
                                Arrays.asList(new Item(1L, "arma", Integer.valueOf("1")),
                                        new Item(2L, "municao", Integer.valueOf("2"))))));

    }

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

    public Rebel save(Rebel rebel) {
        rebel.setId(UUID.randomUUID());
        rebels.add(rebel);
        return rebel;
    }

    public List<Rebel> getAll() {
        return rebels;
    }

//    public Rebel findById(UUID id) {
//
//        Rebel returnedRebel = rebels.stream()
//                .filter(rebel -> rebel.getId().equals(id))
//                .findFirst().get();
//        System.out.println(returnedRebel);
//        return returnedRebel;
//    }

public Rebel findById(UUID id) {

        Rebel returnedRebel = rebels.stream()
                .filter(rebel ->
                        rebel.getId().equals(id))
                .findFirst().get();
        System.out.println(returnedRebel);
        return returnedRebel;
    }

/*    public Optional<Rebel> findById(UUID id) {
        Optional<Rebel> existedRebel = rebels.stream()
                .filter(rebel -> rebel.getId().equals(id))
        return existedRebel;
    }*/


    public Rebel updateRebelLocation(UUID id, Location location) {
        Rebel rebelToReturn = new Rebel();
        for (Rebel rebel : rebels) {
            if (rebel.getId().equals(id)) {
                rebel.setLocation(location);
                rebelToReturn = rebel;
                break;
            }
        }

        return rebelToReturn;
    }

    public String reportRebel(ReportDto report) {
        Rebel potentialTraidor = findById(report.getTraitorId());
        potentialTraidor.report();
        return "Your report was accepted";
    }
}

