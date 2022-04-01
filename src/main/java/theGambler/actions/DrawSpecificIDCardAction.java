package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.util.Wiz;

import java.util.Iterator;

public class DrawSpecificIDCardAction extends AbstractGameAction {
    private AbstractPlayer p;
    private String IDmatch;

    public DrawSpecificIDCardAction(String IDmatch) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.IDmatch = IDmatch;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            int counter = 0;
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            for (AbstractCard q : Wiz.getAllCardsInCardGroups(false, false)) {
                if (q.cardID.equals(IDmatch)) {
                    tmp.addToRandomSpot(q);
                    counter++;
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }


            for (int i = 0; i < counter; ++i) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    AbstractCard card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    if (this.p.hand.size() == 10) {
                        this.p.createHandIsFullDialog();
                    } else {
                        p.drawPile.group.remove(card);
                        p.drawPile.addToTop(card);
                        this.addToBot(new DrawCardAction(1));
                    }
                }
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
