package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Item;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<Rebel> getAll() {
        return repository.getAll();

    }

    public RebelReturnDto save(RebelDto rebelDto) {
        var rebel = new Rebel();
        BeanUtils.copyProperties(rebelDto, rebel);

        repository.save(rebel);

        var rebelReturn = new RebelReturnDto();
        BeanUtils.copyProperties(rebel, rebelReturn);
        return rebelReturn;
    }

    public RebelReturnDto setRebelCurrentLocation(UUID id, CurrentLocationDto currentLocationDto) {

        Location location = new Location();
        BeanUtils.copyProperties(currentLocationDto, location);

        RebelReturnDto returnedRebelDto = new RebelReturnDto();
        Rebel rebel = repository.updateRebelLocation(id, location);
        BeanUtils.copyProperties(rebel, returnedRebelDto);

        return returnedRebelDto;
    }

    public String reportRebel(ReportDto report) {
       return repository.reportRebel(report);

    }

    public List<Deal> getAllOpenDeals() {
        return repository.getAllOpenDeals();
    }

    public ReturnDealDto addOffer(DealDto dealDto) {
        Deal newOffer = new Deal();
        BeanUtils.copyProperties(dealDto, newOffer);
        Deal savedOffer = repository.addOffer(newOffer);
        ReturnDealDto returnDealDto = new ReturnDealDto();
        BeanUtils.copyProperties(savedOffer, returnDealDto);
        return returnDealDto;
    }

    public Rebel makeADeal(CounterpartyDto counterpartyDto) {
        Deal deal = repository.getDealById(counterpartyDto.getDealId());
        Rebel party = repository.findById(deal.getPartyId());
        Rebel counterparty = repository.findById(counterpartyDto.getCounterpartyId());

        exchangeItems(deal, party, counterparty);

        counterparty = repository.updateInventory(counterparty);
        repository.updateInventory(party);
        repository.removeDeal(deal);

        return counterparty;
    }

    private void exchangeItems(Deal deal, Rebel party, Rebel counterparty) {
        List<Item> offeredByParty = deal.getOffer();
        List<Item> demandedByParty = deal.getDemand();
        updateInventory(party, offeredByParty, demandedByParty);
        updateInventory(counterparty, demandedByParty, offeredByParty);
    }

    private Rebel updateInventory(Rebel rebel, List<Item> itemsToRemove, List<Item> itemsToAdd) {
        rebel.getInventory().addAll(itemsToAdd);
        for (Item item : itemsToRemove) {
            rebel.getInventory().remove(item);
        }
        return rebel;
    }
}
