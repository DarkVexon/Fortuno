package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CallYourBluff extends AbstractFortunoCard {
    public final static String ID = makeID("CallYourBluff");
    // intellij stuff skill, self, common, , , 6, 1, , 

    public CallYourBluff() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard q = AbstractDungeon.player.drawPile.getBottomCard();
                    q.updateCost(upgraded ? -999 : -1);
                }
            }
        });
    }

    public void upp() {
        upgradeBlock(1);
        uDesc();
    }
}