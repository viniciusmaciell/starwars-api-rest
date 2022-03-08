package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.dtos.RebelReturnDto;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<Rebel> getAll() {
        return repository.getAll();

    }

    public Rebel cadastrar(Rebel rebel) {
        repository.cadastrar(rebel);
        return rebel;
    }

    public RebelReturnDto setRebelCurrentLocation(Long id, CurrentLocationDto currentLocationDto) {
        System.out.println("service setCurrentLocation");

        RebelReturnDto returnedRebelDto = new RebelReturnDto();
        Location location = new Location(currentLocationDto.getLatitude(),
                currentLocationDto.getLongitude(), currentLocationDto.getBaseName());

        Rebel rebel = repository.updateRebelLocation(id, location);

        returnedRebelDto.setId(rebel.getId());
        returnedRebelDto.setName(rebel.getName());
        returnedRebelDto.setAge(rebel.getAge());
        returnedRebelDto.setGender(rebel.getGender());
        returnedRebelDto.setLocation(rebel.getLocation());

        return returnedRebelDto;
    }
}
