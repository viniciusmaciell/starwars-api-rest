package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.models.*;
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
                        new Exchange(UUID.randomUUID(),
                                Arrays.asList(new Item(UUID.randomUUID(), "arma", Integer.valueOf("1")),
                                        new Item(UUID.randomUUID(), "municao", Integer.valueOf("2"))),
                                Arrays.asList(new Item(UUID.randomUUID(), "arma", Integer.valueOf("1")),
                                        new Item(UUID.randomUUID(), "municao", Integer.valueOf("2"))))));

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

    public boolean isReliableRebel(UUID id) {
        boolean response = false;

        for (Rebel rebel : rebels) {
            if (rebel.getId().equals(id)) {
                response = true;
            }
        }
        return response;
    }

    public boolean isAlreadyTraitor(UUID id) {
        boolean response = false;

        for (Rebel rebel : traidorsRebels) {
            if (rebel.getId().equals(id)) {
                response = true;
            }
        }
        return response;
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

    public List<Exchange> getAllOpenOffers() {
        return openOffers;
    }

    public Exchange addOffer(Exchange newOffer) {
        newOffer.setId(UUID.randomUUID());
        openOffers.add(newOffer);
        return newOffer;
    }
}

