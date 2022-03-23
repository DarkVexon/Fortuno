package theGambler.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.RepeatCardAction;
import theGambler.wheel.Wheel;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.att;
import static theGambler.wheel.Wheel.BASE_DMG_BLOCK;

public class CallousClubPower extends AbstractEasyPower implements OnSpinWheelPower {
    public static String ID = makeID(CallousClubPower.class.getSimpleName());

    public CallousClubPower(int amount) {
        super("Callous Club", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onSpinWheel(ArrayList<AbstractCard> results, boolean red) {
        flash();
        if (!results.isEmpty()) {
            for (int i = 0; i < amount; i++)
                for (AbstractCard q : results) {
                    AbstractDungeon.actionManager.addToTop(new RepeatCardAction(q));
                }
            Wheel.slots.get(Wheel.slots.indexOf(results)).clear();
        }
        else {
            if (red) {
                att(new DamageRandomEnemyAction(new DamageInfo((null), BASE_DMG_BLOCK, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            } else {
                att(new GainBlockAction(AbstractDungeon.player, BASE_DMG_BLOCK));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you #gSpin #gthe #gWheel and land on cards, repeat their effects and #rpermanently #rremove #rthem.";
        else {
            description = "Whenever you #gSpin #gthe #gWheel and land on cards, repeat their effects #b" + amount + " times and #rpermanently #rremove #rthem.";
        }
    }
}