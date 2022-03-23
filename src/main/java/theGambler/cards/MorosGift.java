package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Seek;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.EasyModalChoiceAction;
import theGambler.cards.AbstractFortunoCard;
import theGambler.util.Wiz;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class MorosGift extends AbstractFortunoCard {
    public final static String ID = makeID("MorosGift");
    // intellij stuff skill, self, rare, , , , , 50, -25

    public MorosGift() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 50;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choiceList = new ArrayList<>();
        choiceList.add(new EasyModalChoiceCard("Seek", "Choose 2 cards in your draw pile and add them into your hand.", () -> att(new SeekAction(2))));
        choiceList.add(new EasyModalChoiceCard("Gamble", "Add 3 random cards from other colors into your hand. NL They cost 0.", () -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractCard> lis = Wiz.getCardsMatchingPredicate(q -> q.color != CardColor.CURSE && q.type != CardType.STATUS && q.type != CardType.CURSE, true);
                for (int i = 0; i < 3; i++){
                    AbstractCard tar = Wiz.getRandomItem(lis).makeCopy();
                    tar.updateCost(-99);
                    att(new MakeTempCardInHandAction(tar, true));
                }
            }
        })));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.loseGold(magicNumber);
            }
        });
        atb(new EasyModalChoiceAction(choiceList));
    }

    public void upp() {
        exhaust = false;
        upgradeMagicNumber(-25);
        uDesc();
    }
}