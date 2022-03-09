package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.dtos.RebelDto;
import br.com.letscode.api.starwars.dtos.RebelReturnDto;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.services.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rebels")
public class RebelController {

    private final RebelService service;

    public RebelController(RebelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RebelDto rebel) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(rebel));

    }

    @GetMapping("/list")
    public List<Rebel> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RebelReturnDto> setCurrentLocation(@PathVariable("id") UUID id,
                                                             @RequestBody CurrentLocationDto currentLocationDto) {
        System.out.println("controller setCurrentLocation");
        return ResponseEntity.status(HttpStatus.OK).body(service.setRebelCurrentLocation(id, currentLocationDto));

    }
}
