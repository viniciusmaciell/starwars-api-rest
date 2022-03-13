package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.*;
import br.com.letscode.api.starwars.models.Deal;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.services.RebelService;
import br.com.letscode.api.starwars.utils.ReportMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Object> saveRebel( @Valid @RequestBody RebelDto rebelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRebel(rebelDto));
    }

    @PostMapping("/report")
    public ResponseEntity<String> reportRebel(@RequestBody ReportDto reportDto) {
        if (!service.isARebel(reportDto.getRebelId())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ReportMessageEnum.NOT_VALIDATED.toString());
        }
        if (service.isAlreadyTraitor(reportDto.getTraitorId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This rebel is already a traitor.");
        }
        if (!service.isARebel(reportDto.getTraitorId())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This is not a potential traitor.");
        }
        if (service.isNotUniqueReport(reportDto)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You have already report this rebel.");
        }
        service.reportRebel(reportDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Your report was accepted.");

    }

    @PostMapping("/propose-deal")
    public ResponseEntity<ReturnDealDto> proposeADeal(@RequestBody DealDto exchangeDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addOffer(exchangeDto));
    }

    @PostMapping("/make-deal")
    public ResponseEntity<Object> makeADeal(@RequestBody CounterpartyDto counterpartyDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.makeADeal(counterpartyDto));
    }

    @GetMapping("/list")
    public List<Rebel> getAll() {
        return service.getAll();
    }

    @GetMapping("/open-deals")
    public List<Deal> getAllOpenDeals() {
        return service.getAllOpenDeals();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RebelReturnDto> setCurrentLocation(@PathVariable("id") UUID id,
                                                             @RequestBody CurrentLocationDto currentLocationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setRebelCurrentLocation(id, currentLocationDto));

    }
}
