package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.actions.BotdeckAction;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToSelf;
import static theGambler.util.Wiz.atb;

public class CountingCards extends AbstractFortunoCard {
    public final static String ID = makeID("CountingCards");
    // intellij stuff rare, self, rare, , , , , , 

    public CountingCards() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Counting Cards", AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atStartOfTurnPostDraw() {
                flash();
                atb(new DrawCardAction(amount));
                atb(new BotdeckAction(amount));
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, draw #b" + amount + (amount == 1 ? " card," : " cards,") + " then put #b" + (amount == 1 ? " card" : " cards") + " on the bottom of your draw pile.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}