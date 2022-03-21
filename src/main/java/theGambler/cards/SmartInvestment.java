package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class SmartInvestment extends AbstractFortunoCard {
    public final static String ID = makeID("SmartInvestment");
    // intellij stuff skill, self, rare, , , , , , 

    public SmartInvestment() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
    }
}