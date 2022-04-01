package theGambler.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.shuffleIn;

public class SmartInvestment extends AbstractFortunoCard {
    public final static String ID = makeID("SmartInvestment");
    // intellij stuff skill, self, rare, , , , , , 

    public SmartInvestment() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new PileOfGold();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new PileOfGold();
        if (upgraded) q.upgrade();
        shuffleIn(q);
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upp() {

    }
}