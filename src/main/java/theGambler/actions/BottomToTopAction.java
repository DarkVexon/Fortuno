package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

public class BottomToTopAction extends AbstractGameAction {
    private CardGroup toManipulate;

    public BottomToTopAction(CardGroup toManipulate)
    {
        this.toManipulate = toManipulate;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (toManipulate.size() > 1)
        {
            AbstractCard c = toManipulate.getBottomCard();
            toManipulate.removeCard(c);
            toManipulate.addToTop(c);
        }
        this.isDone = true;
    }
}
