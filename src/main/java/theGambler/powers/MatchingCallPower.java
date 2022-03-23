package theGambler.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theGambler.FortunoMod.makeID;

public class MatchingCallPower extends AbstractEasyPower {
    public static String ID = makeID(MatchingCallPower.class.getSimpleName());

    public MatchingCallPower(int amount) {
        super("Matching Call", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }


    @Override
    public void updateDescription() {
        description = amount == 1 ? "Whenever an enemy gains #yStrength, gain that much #yStrength." : ("Whenever an enemy gains Strength, gain #b" + amount + " times that much #yStrength.");
    }
}