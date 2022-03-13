package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.*;
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
    public ResponseEntity<Object> saveRebel(@Valid @RequestBody RebelDto rebelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRebel(rebelDto));
    }

    @PostMapping("/report")
    public ResponseEntity<String> reportRebel(@Valid @RequestBody ReportDto reportDto) {
        if (!service.isARebel(reportDto.getRebelId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ReportMessageEnum.NOT_VALIDATED.toString());
        }
        if (service.isAlreadyTraitor(reportDto.getTraitorId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ReportMessageEnum.ALREADY_TRAITOR.toString());
        }
        if (!service.isARebel(reportDto.getTraitorId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ReportMessageEnum.NOT_POTENTIAL_TRAITOR.toString());
        }
        if (service.isNotUniqueReport(reportDto)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ReportMessageEnum.ALREADY_REPORTED.toString());

        }
        service.reportRebel(reportDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ReportMessageEnum.REPORT_ACCEPTED.toString());

    }

    @PostMapping("/propose-deal")
    public ResponseEntity<ReturnDealDto> proposeADeal(@Valid @RequestBody DealDto exchangeDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addOffer(exchangeDto));
    }

    @PostMapping("/make-deal")
    public ResponseEntity<RebelReturnDto> makeADeal(@Valid @RequestBody CounterpartyDto counterpartyDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.makeADeal(counterpartyDto));
    }

    @GetMapping("/list")
    public List<RebelReturnDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/open-deals")
    public List<ReturnDealDto> getAllOpenDeals() {
        return service.getAllOpenDeals();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RebelReturnDto> setCurrentLocation(@PathVariable("id") UUID id,
                                                             @Valid @RequestBody CurrentLocationDto currentLocationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setRebelCurrentLocation(id, currentLocationDto));

    }
}
