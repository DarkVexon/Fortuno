package theGambler.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;

public class NoUPower extends AbstractEasyPower implements OnLoseBlockPower {
    public static String ID = makeID(NoUPower.class.getSimpleName());

    public NoUPower(int amount) {
        super("No U", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (damageInfo.owner != null && damageInfo.owner instanceof AbstractMonster)  {
            flash();
            addToTop(new DamageAction(damageInfo.owner, new DamageInfo(owner, i * amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
        return i;
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? "Reflect all blocked damage." : ("Whenever you *Block attack damage, deal #b" + amount + " times that much damage back.");
    }
}