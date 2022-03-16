package theGambler.wheel;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theGambler.util.TexLoader;

import static theGambler.FortunoMod.makeID;

public class WheelCampOption extends AbstractCampfireOption {

    public WheelCampOption() {
        this.label = "Ante";
        this.description = "Put a card with Ante from your deck into a Wheel slot.";
        this.img = TexLoader.getTexture("FSDJNFJSDFNJNDSF MISSING");
    }

    @Override
    public void useOption() {
        AbstractDungeon.effectList.add(new CampfireAnteEffect());
    }
}