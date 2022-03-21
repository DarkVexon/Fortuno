package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class Pickpocket extends AbstractFortunoCard {
    public final static String ID = makeID("Pickpocket");
    // intellij stuff skill, enemy, uncommon, , , , , 10, 5

    public Pickpocket() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = Math.min(m.currentBlock, magicNumber);
        int remainder = magicNumber - amt;
        if (m.currentBlock > 0) {
            atb(new RemoveAllBlockAction(m, p));
            atb(new GainBlockAction(p, amt));
        }
        if (remainder > 0)
            atb(new GainGoldAction(remainder));
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}