package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class Swipe extends AbstractFortunoCard {
    public final static String ID = makeID("Swipe");
    // intellij stuff skill, enemy, uncommon, , , , , 2, 1

    public Swipe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentBlock > 0) {
            int total = m.currentBlock;
            atb(new RemoveAllBlockAction(m, p));
            atb(new GainBlockAction(p, total));
        }
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}