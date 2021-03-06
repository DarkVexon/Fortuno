package theGambler.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.RepeatCardAction;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.att;
import static theGambler.wheel.Wheel.BASE_DMG_BLOCK;

public class EyeFromAbovePower extends AbstractEasyPower implements OnSpinWheelPower {
    public static String ID = makeID(EyeFromAbovePower.class.getSimpleName());

    public EyeFromAbovePower(int amount) {
        super("Eye From Above", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onSpinWheel(ArrayList<AbstractCard> results, boolean red) {
        flash();
        if (!results.isEmpty()) {
            for (AbstractCard q : results) {
                AbstractDungeon.actionManager.addToTop(new RepeatCardAction(q));
            }
        }
        else {
            if (red) {
                att(new DamageRandomEnemyAction(new DamageInfo((null), BASE_DMG_BLOCK, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            } else {
                att(new GainBlockAction(AbstractDungeon.player, BASE_DMG_BLOCK));
            }
        }
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        description = "The next #b" + amount + (amount == 1 ? " time" : " times") + " you #gSpin #gthe #gWheel and land on cards, repeat their effects.";
    }
}