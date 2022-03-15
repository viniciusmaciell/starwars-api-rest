package br.com.letscode.api.starwars.integration;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.utils.ItemEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class StarWarsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RebelRepository repository;

    @Test
    void getAllRebelsTest(){
        Rebel rebel1 = new Rebel();
        Rebel rebel2 = new Rebel();
        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(rebel1, rebel2));

        ResponseEntity<RebelReturnDto[]> responseEntity = restTemplate.getForEntity("/rebels/list", RebelReturnDto[].class);

        Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
        Assertions.assertEquals(responseEntity.getBody().length, 2);
    }

    @Test
    void getAllOpenDealsTest(){
        Deal deal1 = new Deal();
        Deal deal2 = new Deal();
        Deal deal3 = new Deal();

        Mockito.when(repository.getAllOpenDeals()).thenReturn(Arrays.asList(deal1, deal2, deal3));

        ResponseEntity<ReturnDealDto[]> responseEntity = restTemplate.getForEntity("/rebels/open-deals", ReturnDealDto[].class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
        Assertions.assertEquals(responseEntity.getBody().length, 3);
    }

    @Test
    void saveRebelTest(){
        Rebel rebel = new Rebel();
        Mockito.when(repository.save(Mockito.any())).thenReturn(rebel);

        RebelDto rebelDto = new RebelDto();
        rebelDto.setName("filo");
        rebelDto.setAge(30);
        rebelDto.setGender(GenderEnum.FEMALE);
        rebelDto.setInventory(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.WATER));
        Location location = new Location();
        location.setLatitude(Integer.valueOf(0));
        location.setLongitude(Integer.valueOf(0));
        location.setBaseName("base");
        rebelDto.setLocation(location);
        ResponseEntity<RebelReturnDto> responseEntity = restTemplate.postForEntity("/rebels",rebelDto,RebelReturnDto.class);

        Assertions.assertEquals(responseEntity.getStatusCodeValue(),201);
    }

    @Test
    void proposeADealTest(){
        Deal deal = new Deal();
        Mockito.when(repository.addOffer(Mockito.any())).thenReturn(deal);

        Rebel rebel = new Rebel();
        rebel.setId(UUID.randomUUID());
        rebel.setName("filo");
        rebel.setAge(30);
        rebel.setGender(GenderEnum.FEMALE);
        rebel.setInventory(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.WATER));
        Location location = new Location();
        location.setLatitude(Integer.valueOf(0));
        location.setLongitude(Integer.valueOf(0));
        location.setBaseName("base");
        rebel.setLocation(location);
        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(rebel));
        Mockito.when(repository.findById(Mockito.any())).thenReturn(rebel);

        DealDto dealDto = new DealDto();
        dealDto.setPartyId(rebel.getId());
        dealDto.setOffer(Arrays.asList(ItemEnum.WEAPON));
        dealDto.setDemand(Arrays.asList(ItemEnum.WATER, ItemEnum.WATER));

        ResponseEntity<ReturnDealDto> responseEntity = restTemplate.postForEntity("/rebels/propose-deal",dealDto,ReturnDealDto.class);

        Assertions.assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    void makeADealTest(){
        Rebel rebel = new Rebel();
        rebel.setId(UUID.randomUUID());
        rebel.setName("filo");
        rebel.setAge(30);
        rebel.setGender(GenderEnum.FEMALE);
        rebel.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.WATER)));
        Location location = new Location();
        location.setLatitude(Integer.valueOf(0));
        location.setLongitude(Integer.valueOf(0));
        location.setBaseName("base");
        rebel.setLocation(location);
        Mockito.when(repository.findById(rebel.getId())).thenReturn(rebel);

        Deal deal = new Deal();
        deal.setDealId(UUID.randomUUID());
        deal.setPartyId(rebel.getId());
        deal.setOffer(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON)));
        deal.setDemand(new ArrayList<>(Arrays.asList(ItemEnum.WATER,ItemEnum.WATER)));
        Mockito.when(repository.getDealById(deal.getDealId())).thenReturn(deal);

        Rebel counterparty = new Rebel();
        counterparty.setId(UUID.randomUUID());
        counterparty.setName("maria");
        counterparty.setAge(30);
        counterparty.setGender(GenderEnum.FEMALE);
        counterparty.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.AMMUNITION, ItemEnum.WATER, ItemEnum.WATER)));
        Location counterpartyLocation = new Location();
        counterpartyLocation.setLatitude(Integer.valueOf(0));
        counterpartyLocation.setLongitude(Integer.valueOf(0));
        counterpartyLocation.setBaseName("base");
        rebel.setLocation(counterpartyLocation);

        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(rebel,counterparty));

        Mockito.when(repository.getAllOpenDeals()).thenReturn(Arrays.asList(deal));

        Mockito.when(repository.findById(counterparty.getId())).thenReturn(counterparty);

        Mockito.when(repository.updateInventory(counterparty)).thenReturn(counterparty);
        Mockito.when(repository.updateInventory(rebel)).thenReturn(rebel);

        CounterpartyDto counterpartyDto = new CounterpartyDto();
        counterpartyDto.setCounterpartyId(counterparty.getId());
        counterpartyDto.setDealId(deal.getDealId());

        ResponseEntity<RebelReturnDto> responseEntity = restTemplate.postForEntity("/rebels/make-deal",counterpartyDto,RebelReturnDto.class);

        Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
    }

}
