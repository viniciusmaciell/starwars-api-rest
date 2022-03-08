package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.dtos.RebelDto;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.services.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rebels")
public class RebelController {

    private final RebelService service;

    public RebelController(RebelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveRebel(@RequestBody RebelDto rebel) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rebel));

    }

    @GetMapping("/list")
    public List<Rebel> getAll() {
        return service.getAll();
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Ola");
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> setCurrentLocation(@PathVariable("id") Long id,
                                                      @RequestBody CurrentLocationDto currentLocationDto) {
        System.out.println("controller setCurrentLocation");
        return ResponseEntity.status(HttpStatus.OK).body(service.setCurrentLocation(id, currentLocationDto));

    }
}
