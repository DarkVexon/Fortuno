package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.EyeFromAbovePower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class EyeFromAbove extends AbstractFortunoCard {
    public final static String ID = makeID("EyeFromAbove");
    // intellij stuff skill, self, rare, , , , , , 1

    public EyeFromAbove() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EyeFromAbovePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}