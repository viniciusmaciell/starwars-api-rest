package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.CurrentLocationDto;
import br.com.letscode.api.starwars.models.RebelModel;
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

    @GetMapping("/list")
    public List<RebelModel> getAll() {
        return service.getAll();
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Ola");
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RebelModel rebel) {
        service.cadastrar(rebel);
        return ResponseEntity.status(HttpStatus.OK).body("cadastrado");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> setCurrentLocation(@RequestParam("id") Long id,
                                                      @RequestBody CurrentLocationDto currentLocationDto) {

        return ResponseEntity.status(HttpStatus.OK).body(service.setCurrentLocation(id, currentLocationDto));

    }
}
