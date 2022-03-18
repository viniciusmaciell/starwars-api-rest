package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.utils.ItemEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class RebelReturnDto {

    private UUID id;
    private String name;
    private Integer age;
    private GenderEnum gender;
    private Location location;
    @JsonProperty("confidence_level")
    private Integer confidenceLevel;
    @JsonProperty("registration_date")
    private LocalDate registrationDate;
    @JsonProperty("reported_rebels_id")
    private Set<UUID> reportedRebelsId = new HashSet<>();
    private List<ItemEnum> inventory;

}
