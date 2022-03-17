package theGambler.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class WildCard extends AbstractFortunoCard {
    public final static String ID = makeID("WildCard");
    // intellij stuff skill, self, rare, , , , , , 

    public WildCard() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsAction(AbstractDungeon.player.masterDeck.group, 1, "Pick a card, any card...", (cards) -> {
            AbstractCard q = cards.get(0).makeStatEquivalentCopy();
            att(new MakeTempCardInHandAction(q, true));
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}