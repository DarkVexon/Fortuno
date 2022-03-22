package theGambler.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.RepeatCardAction;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;

public class EyeFromAbovePower extends AbstractEasyPower implements OnSpinWheelPower {
    public static String ID = makeID(EyeFromAbovePower.class.getSimpleName());

    public EyeFromAbovePower(int amount) {
        super("Eye From Above", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onSpinWheel(ArrayList<AbstractCard> results) {
        if (!results.isEmpty()) {
            flash();
            for (AbstractCard q : results) {
                AbstractDungeon.actionManager.addToTop(new RepeatCardAction(q));
            }
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        description = "The next #b" + amount + (amount == 1 ? " time" : " times") + " you #gSpin #gthe #gWheel and land on cards, repeat their effects.";
    }
}