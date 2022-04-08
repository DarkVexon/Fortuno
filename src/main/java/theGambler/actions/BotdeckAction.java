package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class BotdeckAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int numCards;

    public BotdeckAction(int numCards) {
        this.numCards = numCards;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() <= numCards) {
                for (int i = 0; i < numCards; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToBottomOfDeck(c);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    this.isDone = true;
                }
            } else {
                AbstractDungeon.handCardSelectScreen.open(" to put on the bottom of your draw pile.", numCards, false);

                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for (Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToBottomOfDeck(c)) {
                    c = (AbstractCard) var1.next();
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}
