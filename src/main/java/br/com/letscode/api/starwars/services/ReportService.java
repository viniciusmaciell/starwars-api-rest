package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.ItemsAmountDto;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RebelRepository rebelRepository;

    public String getPercentageOfTrustedRebels() {
        int rebels = rebelRepository.getAll().size();
        int traitorousRebels = rebelRepository.getAllTraitors().size();
        float total;
        String response;

        total = ((float) rebels / (rebels + traitorousRebels)) * 100;

        response = "The percentage of trusted rebels is " + total + " %";
        return response;
    }

    public String getPercentageOfTraitors() {
        int rebels = rebelRepository.getAll().size();
        int traitorousRebels = rebelRepository.getAllTraitors().size();
        float total;
        String response;

        total = ((float) traitorousRebels / (rebels + traitorousRebels)) * 100;
        if (traitorousRebels == 0) {
            response = "There is not registers of traitors yet.";
        }
        response = "The percentage of traitors is " + total + " %";
        return response;
    }

    public ItemsAmountDto getPercentageOfItemsPerRebel() {
        float waterAmount = 0f;
        float ammunitionAmount = 0f;
        float weaponAmount = 0f;
        float foodAmount = 0f;

        var rebels = rebelRepository.getAll();
        int numberOfRebels = rebels.size();

        for (Rebel rebel : rebels) {
            var inventory = rebel.getInventory();
            for (ItemEnum item : inventory) {
                if (item.equals(ItemEnum.WATER))
                    waterAmount++;
                if (item.equals(ItemEnum.WEAPON)) {
                    weaponAmount++;
                }
                if (item.equals(ItemEnum.AMMUNITION))
                    ammunitionAmount++;
                if (item.equals(ItemEnum.FOOD))
                    foodAmount++;
            }
        }
        return new ItemsAmountDto(
                weaponAmount / numberOfRebels,
                ammunitionAmount / numberOfRebels,
                waterAmount / numberOfRebels,
                foodAmount / numberOfRebels);

    }

}
