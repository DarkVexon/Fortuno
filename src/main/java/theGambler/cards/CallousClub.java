package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.CallousClubPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CallousClub extends AbstractFortunoCard {
    public final static String ID = makeID("CallousClub");
    // intellij stuff power, self, uncommon, , , , , , 

    public CallousClub() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CallousClubPower(1));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}