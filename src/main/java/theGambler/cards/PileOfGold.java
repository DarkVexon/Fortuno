package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class PileOfGold extends AbstractFortunoCard {
    public final static String ID = makeID("PileOfGold");
    // intellij stuff skill, self, special, , , , , 30, 10

    public PileOfGold() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 30;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainGoldAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}