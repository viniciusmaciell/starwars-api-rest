package br.com.letscode.api.starwars.integration;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.utils.ItemEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StarWarsIntegrationTest {

    @MockBean
    private RebelRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initializeMocks(){
        Rebel rebel1 = new Rebel();
        rebel1.setId(UUID.fromString("e401dd3d-c8ba-44fa-8b57-9ecf22f72df7"));
        rebel1.setName("filo");
        rebel1.setAge(30);
        rebel1.setGender(GenderEnum.FEMALE);
        rebel1.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.WATER, ItemEnum.FOOD, ItemEnum.AMMUNITION)));
        Location location = new Location();
        location.setLatitude(0);
        location.setLongitude(0);
        location.setBaseName("base");
        rebel1.setLocation(location);
        Mockito.when(repository.findById(rebel1.getId())).thenReturn(rebel1);

        Rebel rebel2 = new Rebel();
        rebel2.setId(UUID.fromString("93396a50-a738-4a72-9a7b-1ef4c0b8fc5e"));
        rebel2.setName("maria");
        rebel2.setAge(30);
        rebel2.setGender(GenderEnum.FEMALE);
        rebel2.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.AMMUNITION, ItemEnum.WATER, ItemEnum.WATER, ItemEnum.FOOD)));
        Location rebel2Location = new Location();
        rebel2Location.setLatitude(0);
        rebel2Location.setLongitude(0);
        rebel2Location.setBaseName("base2");
        rebel2.setLocation(rebel2Location);
        Mockito.when(repository.findById(rebel2.getId())).thenReturn(rebel2);

        Rebel rebel3 = new Rebel();
        rebel3.setId(UUID.fromString("69243b64-9698-4243-b117-63eb6918b218"));
        rebel3.setName("joana");
        rebel3.setAge(30);
        rebel3.setGender(GenderEnum.FEMALE);
        rebel3.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.AMMUNITION, ItemEnum.FOOD, ItemEnum.WATER)));
        Location rebel3Location = new Location();
        rebel3Location.setLatitude(0);
        rebel3Location.setLongitude(0);
        rebel3Location.setBaseName("base3");
        rebel3.setLocation(rebel3Location);
        Mockito.when(repository.findById(rebel3.getId())).thenReturn(rebel3);

        Rebel rebel4 = new Rebel();
        rebel4.setId(UUID.fromString("e9b3d36d-6c66-467a-b2c4-178ad7336679"));
        rebel4.setName("judas");
        rebel4.setAge(30);
        rebel4.setGender(GenderEnum.MALE);
        rebel4.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.AMMUNITION, ItemEnum.WEAPON, ItemEnum.WEAPON)));
        Location rebel4Location = new Location();
        rebel4Location.setLatitude(0);
        rebel4Location.setLongitude(0);
        rebel4Location.setBaseName("base3");
        rebel4.setLocation(rebel3Location);
        Mockito.when(repository.findById(rebel3.getId())).thenReturn(rebel3);

        Deal deal1 = new Deal();
        deal1.setDealId(UUID.fromString("b15958cc-aa2c-4580-a074-0e804fafdcbd"));
        deal1.setPartyId(rebel1.getId());
        deal1.setOffer(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON)));
        deal1.setDemand(new ArrayList<>(Arrays.asList(ItemEnum.WATER,ItemEnum.WATER)));
        Mockito.when(repository.getDealById(deal1.getDealId())).thenReturn(deal1);

        Deal deal2 = new Deal();
        Deal deal3 = new Deal();

        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(rebel1, rebel2, rebel3, rebel4));
        Mockito.when(repository.getAllOpenDeals()).thenReturn(Arrays.asList(deal1, deal2, deal3));
        Mockito.when(repository.addOffer(Mockito.any())).thenReturn(deal1);

        Mockito.when(repository.findById(rebel2.getId())).thenReturn(rebel2);

        Mockito.when(repository.updateInventory(rebel2)).thenReturn(rebel2);
        Mockito.when(repository.updateInventory(rebel1)).thenReturn(rebel1);

    }
    @Test
    void getAllRebelsTest() throws Exception {
        mockMvc.perform(get("/rebels/list").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(4));
    }

    @Test
    void getAllOpenDealsTest() throws Exception {
        mockMvc.perform(get("/rebels/open-deals").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(3));
    }

    @Test
    void saveRebelTest() throws Exception {
        RebelDto rebelDto = new RebelDto();
        rebelDto.setName("filo");
        rebelDto.setAge(30);
        rebelDto.setGender(GenderEnum.FEMALE);
        rebelDto.setInventory(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.WATER));
        Location location = new Location();
        location.setLatitude(0);
        location.setLongitude(0);
        location.setBaseName("base");
        rebelDto.setLocation(location);

        ObjectMapper objectMapper = new ObjectMapper();
        String rebelDtoAsString = objectMapper.writeValueAsString(rebelDto);
        mockMvc.perform(post("/rebels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rebelDtoAsString))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void saveDealTest() throws Exception {
        DealDto dealDto = new DealDto();
        dealDto.setPartyId(UUID.fromString("e401dd3d-c8ba-44fa-8b57-9ecf22f72df7"));
        dealDto.setOffer(Arrays.asList(ItemEnum.WEAPON));
        dealDto.setDemand(Arrays.asList(ItemEnum.WATER, ItemEnum.WATER));

        ObjectMapper objectMapper = new ObjectMapper();
        String dealDtoAsString = objectMapper.writeValueAsString(dealDto);
        mockMvc.perform(post("/rebels/propose-deal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dealDtoAsString))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void executeDealTest() throws Exception {
        CounterpartyDto counterpartyDto = new CounterpartyDto();
        counterpartyDto.setCounterpartyId(UUID.fromString("93396a50-a738-4a72-9a7b-1ef4c0b8fc5e"));
        counterpartyDto.setDealId(UUID.fromString("b15958cc-aa2c-4580-a074-0e804fafdcbd"));

        ObjectMapper objectMapper = new ObjectMapper();
        String counterpartyDtoAsString = objectMapper.writeValueAsString(counterpartyDto);
        mockMvc.perform(post("/rebels/make-deal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(counterpartyDtoAsString))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void reportRebelTest() throws Exception {
        ReportDto reportDto = new ReportDto();
        reportDto.setRebelId(UUID.fromString("e401dd3d-c8ba-44fa-8b57-9ecf22f72df7"));
        reportDto.setTraitorId(UUID.fromString("e9b3d36d-6c66-467a-b2c4-178ad7336679"));

        ObjectMapper objectMapper = new ObjectMapper();
        String reportDtoAsString = objectMapper.writeValueAsString(reportDto);
        mockMvc.perform(post("/rebels/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reportDtoAsString))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void updateRebelLocationTest() throws Exception {
        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(1);
        locationDto.setLongitude(1);
        locationDto.setBaseName("new base");

        Rebel updatedRebel1 = new Rebel();
        updatedRebel1.setId(UUID.fromString("e401dd3d-c8ba-44fa-8b57-9ecf22f72df7"));
        Location newLocation = new Location();
        newLocation.setLatitude(1);
        newLocation.setLongitude(1);
        newLocation.setBaseName("new base");
        updatedRebel1.setLocation(newLocation);
        Mockito.when(repository.updateRebelLocation(UUID.fromString("e401dd3d-c8ba-44fa-8b57-9ecf22f72df7"), newLocation))
                .thenReturn(updatedRebel1);

        ObjectMapper objectMapper = new ObjectMapper();
        String locationDtoAsString = objectMapper.writeValueAsString(locationDto);
        mockMvc.perform(patch("/rebels/e401dd3d-c8ba-44fa-8b57-9ecf22f72df7")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(locationDtoAsString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location.latitude").value(1))
                .andExpect(jsonPath("$.location.longitude").value(1))
                .andExpect(jsonPath("$.location.baseName").value("new base"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getPercentageOfTrustedRebelsTest() throws Exception {
        Rebel traitor1 = new Rebel();
        Rebel traitor2 = new Rebel();

        Mockito.when(repository.getAllTraitors()).thenReturn(Arrays.asList(traitor1,traitor2));

        Assertions.assertEquals("The percentage of trusted rebels is " + (4f/6*100),
                                mockMvc.perform(get("/report/reliable-rebels")
                                                .contentType(MediaType.APPLICATION_JSON))
                                        .andDo(print())
                                        .andExpect(status().isOk())
                                        .andReturn().getResponse().getContentAsString());
    }

    @Test
    void getPercentageOfTraitorsTest() throws Exception {
        Rebel traitor1 = new Rebel();
        Rebel traitor2 = new Rebel();

        Mockito.when(repository.getAllTraitors()).thenReturn(Arrays.asList(traitor1,traitor2));

        Assertions.assertEquals("The percentage of traitors is " + (2f/6*100),
                mockMvc.perform(get("/report/traitors")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString());
    }

    @Test
    void getAmountOfItemsPerRebelTest() throws Exception {
        mockMvc.perform(get("/report/rebels/amount-items").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weapon").value(3f/4))
                .andExpect(jsonPath("$.water").value(5f/4))
                .andExpect(jsonPath("$.food").value(3f/4))
                .andExpect(jsonPath("$.ammunition").value(4f/4));
    }

    @Test
    void getAllMissedPointsTest() throws Exception{
        Rebel traitor1 = new Rebel();
        traitor1.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.FOOD, ItemEnum.FOOD, ItemEnum.AMMUNITION)));
        Rebel traitor2 = new Rebel();
        traitor2.setInventory(new ArrayList<>(Arrays.asList(ItemEnum.WEAPON, ItemEnum.WATER, ItemEnum.AMMUNITION, ItemEnum.AMMUNITION)));

        Mockito.when(repository.getAllTraitors()).thenReturn(Arrays.asList(traitor1,traitor2));

        mockMvc.perform(get("/report/traitors/missed-points").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weapon").value(-8))
                .andExpect(jsonPath("$.water").value(-4))
                .andExpect(jsonPath("$.food").value(-2))
                .andExpect(jsonPath("$.ammunition").value(-9))
                .andExpect(jsonPath("$.total_points_lost").value(23));
    }
}
