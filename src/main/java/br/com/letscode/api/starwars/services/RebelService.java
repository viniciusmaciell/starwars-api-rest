package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.dtos.RebelDto;
import br.com.letscode.api.starwars.models.LocationModel;
import br.com.letscode.api.starwars.models.RebelModel;
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

    public List<RebelModel> getAll() {
        return repository.getAll();

    }

    public RebelModel cadastrar(RebelModel rebel) {
        repository.cadastrar(rebel);
        return rebel;
    }

    public RebelDto setCurrentLocation(Long id, CurrentLocationDto currentLocationDto) {
        System.out.println("service setCurrentLocation");
        RebelModel rebel = repository.findById(id);
        RebelDto rebelDto = new RebelDto();
        LocationModel location = new LocationModel(currentLocationDto.getLatitude(),
                currentLocationDto.getLongitude(), currentLocationDto.getBaseName());
        rebel.setLocation(location);
        repository.cadastrar(rebel);
        System.out.println("service - rebel: " + rebel);
        BeanUtils.copyProperties(rebel, rebelDto);
        return rebelDto;
    }
}
