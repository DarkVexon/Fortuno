package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theGambler.actions.AllEnemyLoseHPAction;
import theGambler.actions.EasyModalChoiceAction;
import theGambler.cards.AbstractFortunoCard;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class QuickStopShop extends AbstractFortunoCard {
    public final static String ID = makeID("QuickStopShop");
    // intellij stuff skill, self, rare, , , , , 40, -15

    public QuickStopShop() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 40;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.loseGold(magicNumber);
            }
        });
        ArrayList<AbstractCard> cardList = new ArrayList<>();
        cardList.add(new EasyModalChoiceCard("Buy Drink", "Gain a random Potion.", () -> {
                AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion(true));
        }));
        cardList.add(new EasyModalChoiceCard("Buy Card", "Gain an additional card reward this combat.", () -> {
            AbstractDungeon.getCurrRoom().addCardToRewards();
        }));
        cardList.add(new EasyModalChoiceCard("Buy Pain", "ALL enemies lose 13 HP.", () -> {
            att(new AllEnemyLoseHPAction(13));
        }));
        atb(new EasyModalChoiceAction(cardList));
    }

    public void upp() {
        upgradeMagicNumber(-15);
    }
}