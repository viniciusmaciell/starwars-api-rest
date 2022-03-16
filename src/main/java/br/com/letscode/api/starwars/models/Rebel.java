package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
public class Rebel {

    private UUID id;
    private String name;
    private Integer age;
    private GenderEnum gender;
    private Integer confidenceLevel;
    private Location location;
    private LocalDate registrationDate;
    private List<ItemEnum> inventory = new ArrayList<>();
    private Set<UUID> reportedRebelsId = new HashSet<>();

    public Rebel() {
        this.confidenceLevel = 1;
    }

    public void decrementConfidenceLevel() {
        confidenceLevel--;
    }

    public void report(UUID id) {
        reportedRebelsId.add(id);
    }

    public boolean hasAlreadyBeenReported(UUID id) {
        return reportedRebelsId.contains(id);
    }

    public void hasItems(List<ItemEnum> items) {
        if(!this.getInventory().containsAll(items)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You don't have the items to make the deal.");
        }
    }

    public void isReliable(){
        if(this.confidenceLevel <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You're a traitor. You can't make a deal.");
        }
    }
}
