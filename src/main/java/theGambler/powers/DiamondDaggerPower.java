package theGambler.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;

public class DiamondDaggerPower extends AbstractEasyPower implements OnSpinWheelPower, NonStackablePower {
    public static String ID = makeID(DiamondDaggerPower.class.getSimpleName());

    public DiamondDaggerPower(int location) {
        super("Diamond Dagger", PowerType.BUFF, false, AbstractDungeon.player, location);
    }

    @Override
    public void onSpinWheel(ArrayList<AbstractCard> results, boolean isRed) {
        flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = "The #gWheel will land on slot #b" + amount + " next.";
    }
}