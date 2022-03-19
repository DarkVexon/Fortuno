package theGambler.cards;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class MirrorFaced extends AbstractFortunoCard {
    public final static String ID = makeID("MirrorFaced");
    // intellij stuff skill, self, uncommon, , , , , , 

    public MirrorFaced() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard q : DrawCardAction.drawnCards) {
                    AbstractCard q2 = q.makeStatEquivalentCopy();
                    if (upgraded) {
                        q2.updateCost(-1);
                    }
                    att(new MakeTempCardInHandAction(q2, true));
                }
            }
        }));
    }

    public void upp() {
        uDesc();
    }
}