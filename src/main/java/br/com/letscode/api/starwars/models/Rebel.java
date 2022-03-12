package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Rebel {

    private UUID id;
    private String name;
    private Integer age;
    private GenderEnum gender;
    private Integer confidenceLevel;
    private Location location;
    private LocalDate registrationDate;
    private List<Item> inventory = new ArrayList<>();
    private Set<UUID> reportedRebelsId = new HashSet<>();

    public Rebel() {
        this.confidenceLevel = 2;
    }

    public void decrementConfidenceLevel() {
        confidenceLevel--;
    }

    public void reportRebel(UUID id) {
     reportedRebelsId.add(id);
    }

    public boolean hasAlreadyBeenReported(UUID id) {
        boolean response = false;
        for (UUID complaint : reportedRebelsId) {
            if (complaint.equals(id)) {
                response = true;

            }
        }
        return response;
    }
}
