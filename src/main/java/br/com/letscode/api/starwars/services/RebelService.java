package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.dtos.RebelDto;
import br.com.letscode.api.starwars.dtos.RebelReturnDto;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import org.springframework.beans.BeanUtils;
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
//
//    public Rebel cadastrar(Rebel rebel) {
//        repository.cadastrar(rebel);
//        return rebel;
//    }

    public RebelDto setCurrentLocation(Long id, CurrentLocationDto currentLocationDto) {
        System.out.println("service setCurrentLocation");
        Rebel rebel = repository.findById(id);
        RebelDto rebelDto = new RebelDto();
        Location location = new Location(currentLocationDto.getLatitude(),
                currentLocationDto.getLongitude(), currentLocationDto.getBaseName());
        rebel.setLocation(location);
        repository.save(rebel);
        System.out.println("service - rebel: " + rebel);
        BeanUtils.copyProperties(rebel, rebelDto);
        return rebelDto;
    }

    public Rebel save(RebelDto rebel) {
        Rebel rebelEntity = new Rebel();
        BeanUtils.copyProperties(rebel, rebelEntity);
        return repository.save(rebelEntity);
    }
}

