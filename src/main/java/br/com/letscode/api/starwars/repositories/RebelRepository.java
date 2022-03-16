package br.com.letscode.api.starwars.repositories;

import br.com.letscode.api.starwars.dtos.ReportDto;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RebelRepository {

    private static List<Rebel> rebels = new ArrayList<>();

    private static List<Rebel> traidorsRebels = new ArrayList<>();

    private static List<Deal> openOffers = new ArrayList<>();

    public Rebel save(Rebel rebel) {
        rebel.setId(UUID.randomUUID());
        rebels.add(rebel);
        return rebel;
    }

    public List<Rebel> getAll() {
        return rebels;
    }

    public List<Rebel> getAllTraitors() {
        return traidorsRebels;
    }

    public Rebel findById(UUID id) {
        Rebel returnedRebel = rebels.stream()
                .filter(rebel ->
                        rebel.getId().equals(id))
                .findFirst().get();
        return returnedRebel;
    }

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

    public List<Deal> getAllOpenDeals() {
        return openOffers;
    }

    public Deal addOffer(Deal newOffer) {
        newOffer.setDealId(UUID.randomUUID());
        openOffers.add(newOffer);
        return newOffer;
    }

    public Deal getDealById(UUID id) {
        return openOffers.stream()
                .filter(offer ->
                        offer.getDealId().equals(id))
                .findFirst().get();
    }

    public Rebel updateInventory(Rebel rebel) {
        Rebel rebelToReturn = new Rebel();
        for (Rebel currentRebel : rebels) {
            if (currentRebel.getId().equals(rebel.getId())) {
                currentRebel.setInventory(rebel.getInventory());
                rebelToReturn = rebel;
                break;
            }
        }
        return rebelToReturn;
    }

    public void removeDeal(Deal deal) {
        openOffers.remove(getDealById(deal.getDealId()));
    }

    public void denounceRebel(ReportDto report) {

        for (Rebel rebel : rebels) {
            if (rebel.getId().equals(report.getRebelId())) {
                rebel.report(report.getTraitorId());

            }
            if (rebel.getId().equals(report.getTraitorId())) {
                rebel.decrementConfidenceLevel();
                if (rebel.getConfidenceLevel().equals(0)) {
                    markAsTraitor(rebel);
                    break;
                }
            }
        }
    }

    private void markAsTraitor(Rebel rebel) {
        traidorsRebels.add(rebel);
        rebels.remove(rebel);
    }

    public boolean isUniqueReport(ReportDto report) {

        Rebel rebel = findById(report.getRebelId());
        return rebel.hasAlreadyBeenReported(report.getTraitorId());
    }

}

