package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Deal {

    private UUID id;
    private UUID partyId;
    private List<Item> offer;
    private List<Item> demand;

    public void pointsMatch(){
        Integer pointsOffer = 0;
        Integer pointsDemand = 0;
        for(Item item : offer){
            pointsOffer = item.getScore();
        }
        for(Item item : demand){
            pointsDemand = item.getScore();
        }
        if(pointsOffer != pointsDemand){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supply and demand must have the same score.");
        }
    }
}
