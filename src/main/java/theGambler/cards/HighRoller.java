package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;

public class HighRoller extends AbstractFortunoCard {
    public final static String ID = makeID("HighRoller");
    // intellij stuff skill, none, basic, , , , , , 

    public HighRoller() {
        super(ID, -2, CardType.CURSE, CardRarity.BASIC, CardTarget.NONE, CardColor.CURSE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "Not the time.";
        return false;
    }

    public void upp() {
    }
}