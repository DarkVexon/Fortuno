package theGambler.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theGambler.FortunoMod.makeID;

public class BlueChipBarrierPower extends AbstractEasyPower implements OnGainGoldPower {
    public static String ID = makeID(BlueChipBarrierPower.class.getSimpleName());

    public BlueChipBarrierPower(int amount) {
        super("BlueChipBarrier", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onGainGold(int gained) {
        flash();
        addToBot(new GainBlockAction(owner, gained * amount));
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? "Whenever you gain Gold during combat, gain that much #yBlock." : ("Whenever you gain Gold during combat, gain #b" + amount + " times the amount gained as #yBlock.");
    }
}