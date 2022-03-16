package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.dtos.ItemsAmountDto;
import br.com.letscode.api.starwars.dtos.LostPointsDto;
import br.com.letscode.api.starwars.models.Rebel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import br.com.letscode.api.starwars.utils.ItemEnum;
import br.com.letscode.api.starwars.utils.RebelCategoryEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RebelRepository rebelRepository;

    public String getPercentageOfTrustedRebels() {

        Float total = getAmountOfAllRebels(RebelCategoryEnum.RELIABLE_REBELS);
        if (total.isNaN())
            return "It was not found any register of rebels.";

        return "The percentage of trusted rebels is " + total;
    }

    public String getPercentageOfTraitors() {
        Float total = getAmountOfAllRebels(RebelCategoryEnum.TRAITORS_REBELS);
        if (total.isNaN())
            return "It was not found any register of traitors.";

        return "The percentage of traitors is " + total;
    }

    public ItemsAmountDto getPercentageOfItemsPerRebel() {
        float waterAmount = 0;
        float ammunitionAmount = 0;
        float weaponAmount = 0;
        float foodAmount = 0;

        var rebels = rebelRepository.getAll();
        int numberOfRebels = rebels.size();

        if (!rebels.isEmpty()) {
            for (Rebel rebel : rebels) {
                var inventory = rebel.getInventory();
                for (ItemEnum item : inventory) {
                    if (item.equals(ItemEnum.WATER))
                        waterAmount++;
                    if (item.equals(ItemEnum.WEAPON))
                        weaponAmount++;
                    if (item.equals(ItemEnum.AMMUNITION))
                        ammunitionAmount++;
                    if (item.equals(ItemEnum.FOOD))
                        foodAmount++;
                }
            }
        }
        return new ItemsAmountDto(
                weaponAmount / numberOfRebels,
                ammunitionAmount / numberOfRebels,
                waterAmount / numberOfRebels,
                foodAmount / numberOfRebels);

    }

    private Float getAmountOfAllRebels(RebelCategoryEnum rebelCategory) {
        int rebels = rebelRepository.getAll().size();
        int traitorsRebels = rebelRepository.getAllTraitors().size();
        float total = 0f;


        if (rebelCategory.equals(RebelCategoryEnum.RELIABLE_REBELS)) {
            total = ((float) rebels / (rebels + traitorsRebels)) * 100;
        }
        if (rebelCategory.equals(RebelCategoryEnum.TRAITORS_REBELS)) {
            total = ((float) traitorsRebels / (rebels + traitorsRebels)) * 100;
        }
        return total;
    }

    public LostPointsDto getAllLostPointsFromTraitors() {
        int water = 0;
        int ammunition = 0;
        int weapon = 0;
        int food = 0;
        int totalLostPoints = 0;

        var traitors = rebelRepository.getAllTraitors();

        for (Rebel traitor : traitors) {
            var inventory = traitor.getInventory();
            for (ItemEnum lostItem : inventory) {

                if (lostItem.equals(ItemEnum.WATER)) {
                    water -= ItemEnum.WATER.getValue();
                    totalLostPoints += ItemEnum.WATER.getValue();

                } else if (lostItem.equals(ItemEnum.WEAPON)) {
                    weapon -= ItemEnum.WEAPON.getValue();
                    totalLostPoints += ItemEnum.WEAPON.getValue();

                } else if (lostItem.equals(ItemEnum.AMMUNITION)) {
                    ammunition -= ItemEnum.AMMUNITION.getValue();
                    totalLostPoints += ItemEnum.AMMUNITION.getValue();

                } else if (lostItem.equals(ItemEnum.FOOD)) {
                    food -= ItemEnum.FOOD.getValue();
                    totalLostPoints += ItemEnum.FOOD.getValue();
                }

            }
        }
        return new LostPointsDto(weapon, ammunition, water, food, totalLostPoints);
    }

}
