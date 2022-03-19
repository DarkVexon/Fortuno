package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class NoMe extends AbstractFortunoCard {
    public final static String ID = makeID("NoMe");
    // intellij stuff skill, self_and_enemy, uncommon, , , , , 2, 1

    public NoMe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(StrengthPower.POWER_ID)) {
            int found = m.getPower(StrengthPower.POWER_ID).amount;
            if (found > 0) {
                int x = Math.min(magicNumber, found);
                applyToEnemy(m, new StrengthPower(m, -x));
                applyToSelf(new StrengthPower(p, x));
            }
        }
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}