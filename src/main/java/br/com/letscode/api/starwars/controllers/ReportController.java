package br.com.letscode.api.starwars.controllers;

import br.com.letscode.api.starwars.dtos.ItemsAmountDto;
import br.com.letscode.api.starwars.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping("/reliable-rebels")
    public ResponseEntity<String> getPercentageOfTrustedRebels() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPercentageOfTrustedRebels());
    }

    @GetMapping("/traitors")
    public ResponseEntity<String> getPercentageOfTraitors() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPercentageOfTraitors());
    }

    @GetMapping("/rebels/amount-items")
    public ResponseEntity<ItemsAmountDto> getAmountOfItemsPerRebel(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPercentageOfItemsPerRebel());
    }

}
