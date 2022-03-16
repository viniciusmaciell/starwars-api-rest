package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.CounterpartyDto;
import br.com.letscode.api.starwars.dtos.DealDto;
import br.com.letscode.api.starwars.dtos.RebelReturnDto;
import br.com.letscode.api.starwars.dtos.ReturnDealDto;
import br.com.letscode.api.starwars.services.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/deals")
public class DealController {

    private final RebelService service;

    public DealController(RebelService service) {
        this.service = service;
    }

    @PostMapping("/save-deal")
    public ResponseEntity<ReturnDealDto> saveDeal(@Valid @RequestBody DealDto exchangeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveDeal(exchangeDto));
    }

    @PostMapping("/execute-deal")
    public ResponseEntity<RebelReturnDto> executeDeal(@Valid @RequestBody CounterpartyDto counterpartyDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.executeDeal(counterpartyDto));
    }

    @GetMapping("/open-deals")
    public ResponseEntity<List<ReturnDealDto>> getAllOpenDeals() {
        return ResponseEntity.ok(service.getAllOpenDeals());
    }
}
