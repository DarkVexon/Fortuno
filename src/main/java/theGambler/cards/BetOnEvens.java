package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class BetOnEvens extends AbstractFortunoCard {
    public final static String ID = makeID("BetOnEvens");
    // intellij stuff power, self, uncommon, , , , , , 

    public BetOnEvens() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Bet On Evens", AbstractPower.PowerType.BUFF, false, p, 2) {
            @Override
            public void onCardDraw(AbstractCard card) {
                if (card.costForTurn % 2 == 0) {
                    flash();
                    att(new GainBlockAction(owner, amount));
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you draw an even-cost card, gain #b" + amount + " #yBlock.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}