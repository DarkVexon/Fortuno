package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.SpinWheelAction;
import theGambler.powers.EyesOnThePrizePower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToSelf;
import static theGambler.util.Wiz.atb;

public class EyesOnThePrize extends AbstractFortunoCard {
    public final static String ID = makeID("EyesOnThePrize");
    // intellij stuff power, self, uncommon, , , , , , 

    public EyesOnThePrize() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EyesOnThePrizePower(1));
        if (upgraded) {
            atb(new SpinWheelAction());
        }
    }

    public void upp() {
        uDesc();
    }
}