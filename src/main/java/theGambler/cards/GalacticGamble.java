package theGambler.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;
import theGambler.util.Wiz;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class GalacticGamble extends AbstractFortunoCard {
    public final static String ID = makeID("GalacticGamble");
    // intellij stuff skill, self, rare, , , , , , 

    public GalacticGamble() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x  = p.hand.size();
        atb(new ExhaustAction(BaseMod.MAX_HAND_SIZE, true, true));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractCard> lis = Wiz.getCardsMatchingPredicate(q -> q.color != CardColor.CURSE && q.type != CardType.STATUS && q.type != CardType.CURSE, true);
                for (int i = 0; i < x; i++){
                    AbstractCard tar = Wiz.getRandomItem(lis).makeCopy();
                    if (upgraded) tar.upgrade();
                    tar.updateCost(-99);
                    att(new MakeTempCardInHandAction(tar, true));
                }
            }
        });
    }

    public void upp() {
        uDesc();
    }
}