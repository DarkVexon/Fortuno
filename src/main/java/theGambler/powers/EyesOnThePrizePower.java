package theGambler.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;

public class EyesOnThePrizePower extends AbstractEasyPower implements OnSpinWheelPower {
    public static String ID = makeID(EyesOnThePrizePower.class.getSimpleName());

    public EyesOnThePrizePower(int amount) {
        super("Eyes on the Prize", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onSpinWheel(ArrayList<AbstractCard> results, boolean red) {
        flash();
        addToBot(new DrawCardAction(amount));
    }

    @Override
    public void updateDescription() {
        description = "Whenever you Spin the Wheel, draw #b" + amount + (amount == 1 ? " card." : " cards.");
    }
}