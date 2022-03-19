package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class NoU extends AbstractFortunoCard {
    public final static String ID = makeID("NoU");
    // intellij stuff power, self, rare, , , , , , 

    public NoU() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("No U", AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void updateDescription() {
                description = amount == 1 ? "Reflect all blocked damage." : ("When you block damage, deal that much damage back #b" + amount + " times.");
            }
        });
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}