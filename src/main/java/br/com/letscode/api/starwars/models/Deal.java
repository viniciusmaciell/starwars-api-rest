package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.ItemEnum;
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

    private UUID dealId;
    private UUID partyId;
    private List<ItemEnum> offer;
    private List<ItemEnum> demand;

    public void pointsMatch() {
        Integer pointsOffer = 0;
        Integer pointsDemand = 0;
        for (ItemEnum item : offer) {
            pointsOffer += Integer.valueOf(item.getValue());
        }
        for (ItemEnum item : demand) {
            pointsDemand += Integer.valueOf(item.getValue());
        }
        if(!pointsOffer.equals(pointsDemand)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supply and demand must have the same score.");
        }
    }
}
