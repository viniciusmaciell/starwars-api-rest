package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import br.com.letscode.api.starwars.utils.ItemEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<RebelReturnDto> getAll() {
        var rebels = repository.getAll();
        return rebels.stream().map(rebel -> {
            var rebelDto = new RebelReturnDto();
            BeanUtils.copyProperties(rebel, rebelDto);
            return rebelDto;
        }).collect(Collectors.toList());
    }

    public RebelReturnDto saveRebel(RebelDto rebelDto) {
        var rebel = new Rebel();
        BeanUtils.copyProperties(rebelDto, rebel);
        rebel.setRegistrationDate(LocalDate.now());
        repository.save(rebel);

        var rebelReturn = new RebelReturnDto();
        BeanUtils.copyProperties(rebel, rebelReturn);
        return rebelReturn;
    }

    public RebelReturnDto setRebelCurrentLocation(UUID id, CurrentLocationDto currentLocationDto) {

        if(!isARebel(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Rebel not found.");
        }

        Location location = new Location();
        BeanUtils.copyProperties(currentLocationDto, location);

        RebelReturnDto returnedRebelDto = new RebelReturnDto();
        Rebel rebel = repository.updateRebelLocation(id, location);
        BeanUtils.copyProperties(rebel, returnedRebelDto);

        return returnedRebelDto;
    }

    public void reportRebel(ReportDto reportDto) {
        repository.reportRebel(reportDto);
    }

    public boolean isARebel(UUID id) {
        if (!repository.getAll().isEmpty()) {
            for (Rebel rebel : repository.getAll()) {
                if (rebel.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAlreadyTraitor(UUID id) {
        if (!repository.getAllTraitors().isEmpty()) {
            for (Rebel rebel : repository.getAllTraitors()) {
                if (rebel.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<ReturnDealDto> getAllOpenDeals() {
        var deals = repository.getAllOpenDeals();
        return deals.stream().map(deal -> {
            var dealDto = new ReturnDealDto();
            BeanUtils.copyProperties(deal, dealDto);
            return dealDto;
        }).collect(Collectors.toList());
    }

    public ReturnDealDto addOffer(DealDto dealDto) {
        if(!isARebel(dealDto.getPartyId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Rebel not found.");
        }
        Deal deal = new Deal();
        BeanUtils.copyProperties(dealDto, deal);
        validateDeal(deal);
        Deal savedOffer = repository.addOffer(deal);
        ReturnDealDto returnDealDto = new ReturnDealDto();
        BeanUtils.copyProperties(savedOffer, returnDealDto);
        return returnDealDto;
    }


    public RebelReturnDto makeADeal(CounterpartyDto counterpartyDto) {
        if(!isARebel(counterpartyDto.getCounterpartyId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Rebel not found.");
        }
        if(!isADeal(counterpartyDto.getDealId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Deal not found.");
        }
        Deal deal = repository.getDealById(counterpartyDto.getDealId());
        Rebel party = repository.findById(deal.getPartyId());
        Rebel counterparty = repository.findById(counterpartyDto.getCounterpartyId());

        counterparty.isReliable();
        counterparty.hasItems(deal.getDemand());

        exchangeItems(deal, party, counterparty);

        counterparty = repository.updateInventory(counterparty);
        repository.updateInventory(party);
        repository.removeDeal(deal);

        RebelReturnDto counterpartyReturnDto = new RebelReturnDto();
        BeanUtils.copyProperties(counterparty, counterpartyReturnDto);

        return counterpartyReturnDto;
    }

    private boolean isADeal(UUID dealId) {
        if (!repository.getAll().isEmpty()) {
            for (Deal deal : repository.getAllOpenDeals()) {
                if (deal.getDealId().equals(dealId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validateDeal(Deal deal) {
        deal.pointsMatch();

        Rebel rebel = repository.findById(deal.getPartyId());
        rebel.isReliable();
        rebel.hasItems(deal.getOffer());
    }

    private void exchangeItems(Deal deal, Rebel party, Rebel counterparty) {
        List<ItemEnum> offeredByParty = deal.getOffer();
        List<ItemEnum> demandedByParty = deal.getDemand();
        updateInventory(party, offeredByParty, demandedByParty);
        updateInventory(counterparty, demandedByParty, offeredByParty);
    }

    private Rebel updateInventory(Rebel rebel, List<ItemEnum> itemsToRemove, List<ItemEnum> itemsToAdd) {
        rebel.getInventory().addAll(itemsToAdd);
        for (ItemEnum item : itemsToRemove) {
            rebel.getInventory().remove(item);
        }
        return rebel;
    }

    public boolean isNotUniqueReport(ReportDto reportDto) {
        return repository.isUniqueReport(reportDto);
    }
}
