package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class BetOnOdds extends AbstractFortunoCard {
    public final static String ID = makeID("BetOnOdds");
    // intellij stuff power, self, uncommon, , , , , 1, 1

    public BetOnOdds() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Bet On Odds", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onCardDraw(AbstractCard card) {
                if (card.costForTurn % 2 == 1) {
                    flash();
                    int x = this.amount;
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractCard q = card.makeStatEquivalentCopy();
                            q.updateCost(-x);
                            shuffleIn(q);
                        }
                    });
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you draw an odd-cost card, shuffle an Ethereal copy that costs #b" + amount + " less into your draw pile.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}