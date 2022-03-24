package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.sun.org.apache.bcel.internal.generic.FALOAD;
import theGambler.actions.SpinWheelAction;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class GamblerForm extends AbstractFortunoCard {
    public final static String ID = makeID("GamblerForm");
    // intellij stuff power, self, rare, , , , , 5, 5

    public GamblerForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Gambler Form", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void atStartOfTurnPostDraw() {
                flash();
                atb(new SpinWheelAction());
                atb(new GainGoldAction(amount));
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, #gSpin #gthe #gWheel and gain #b" + amount + " Gold.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}