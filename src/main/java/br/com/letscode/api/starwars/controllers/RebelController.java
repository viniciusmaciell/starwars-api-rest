package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
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

    @PostMapping("/report")
    public ResponseEntity<Object> reportRebel(@RequestBody ReportDto report) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.reportRebel(report));
    }

    @PostMapping("/propose-deal")
    public ResponseEntity<ReturnDealDto> proposeADeal(@RequestBody DealDto exchangeDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addOffer(exchangeDto));
    }

    @PostMapping("/make-deal")
    public ResponseEntity<Object> makeADeal(@RequestBody CounterpartyDto counterpartyDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.makeADeal(counterpartyDto));
    }

    @GetMapping("/list")
    public List<Rebel> getAll() {
        return service.getAll();
    }

    @GetMapping("/open-deals")
    public List<Deal> getAllOpenDeals(){
        return service.getAllOpenDeals();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RebelReturnDto> setCurrentLocation(@PathVariable("id") UUID id,
                                                             @RequestBody CurrentLocationDto currentLocationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setRebelCurrentLocation(id, currentLocationDto));

    }
}
