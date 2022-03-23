package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.MatchingCallPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class MatchingCall extends AbstractFortunoCard {
    public final static String ID = makeID("MatchingCall");
    // intellij stuff power, self, uncommon, , , , , , 1

    public MatchingCall() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MatchingCallPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}