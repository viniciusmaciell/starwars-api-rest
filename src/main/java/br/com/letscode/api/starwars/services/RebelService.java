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
import java.util.UUID;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<Rebel> getAll() {
        return repository.getAll();

    }

    public RebelReturnDto save(RebelDto rebelDto) {
        var rebel = new Rebel();
        BeanUtils.copyProperties(rebelDto, rebel);
        repository.save(rebel);

        var rebelReturn = new RebelReturnDto();
        BeanUtils.copyProperties(rebel, rebelReturn);
        return rebelReturn;
    }

    public RebelReturnDto setRebelCurrentLocation(UUID id, CurrentLocationDto currentLocationDto) {
        System.out.println("service setCurrentLocation");


        Location location = new Location();
        BeanUtils.copyProperties(currentLocationDto, location);

        RebelReturnDto returnedRebelDto = new RebelReturnDto();
        Rebel rebel = repository.updateRebelLocation(id, location);
        BeanUtils.copyProperties(rebel, returnedRebelDto);


//        returnedRebelDto.setId(rebel.getId());
//        returnedRebelDto.setName(rebel.getName());
//        returnedRebelDto.setAge(rebel.getAge());
//        returnedRebelDto.setGender(rebel.getGender());
//        returnedRebelDto.setLocation(rebel.getLocation());


        return returnedRebelDto;
    }
}
