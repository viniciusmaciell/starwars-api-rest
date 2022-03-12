package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    private List<Item> inventory;
    private List<UUID> complaintsId;

    public Rebel() {
        this.confidenceLevel = 2;
    }

    public void report() {
        confidenceLevel--;
    }

    public void registerComplaint(UUID id) {
        complaintsId.add(id);
    }

    public boolean hasAlreadyBeenReported(UUID id) {
        boolean response = false;
        for (UUID complaint : complaintsId) {
            if (complaint.equals(id)) {
                response = true;

            }
        }
        return response;
    }
}
