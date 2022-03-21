package theGambler.cards;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CounterfeitCards extends AbstractFortunoCard {
    public final static String ID = makeID("CounterfeitCards");
    // intellij stuff power, self, rare, , , , , , 

    public CounterfeitCards() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Counterfeit Cards", AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void onCardDraw(AbstractCard card) {
                if (!card.isEthereal) {
                    flash();
                    AbstractCard q = card.makeStatEquivalentCopy();
                    CardModifierManager.addModifier(q, new EtherealMod());
                    att(new MakeTempCardInHandAction(q));
                }
            }

            @Override
            public void updateDescription() {
                description = amount == 1 ? ("Whenever you draw a #ynon-Ethereal card, create a copy of it.") : ("Whenever you draw a #ynon-Ethereal card, create #b" + amount + " copies of it.");
            }
        });
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}