package theGambler.cards;

import basemod.cardmods.ExhaustMod;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.SpinWheelAction;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class UpMySleeve extends AbstractFortunoCard {
    public final static String ID = makeID("UpMySleeve");
    // intellij stuff skill, none, uncommon, , , , , , 

    public UpMySleeve() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SpinWheelAction(c -> {
            AbstractCard q = c.makeStatEquivalentCopy();
            CardModifierManager.addModifier(q, new RetainMod());
            CardModifierManager.addModifier(q, new ExhaustMod());
            if (upgraded) {
                q.freeToPlayOnce = true;
            }
            att(new MakeTempCardInHandAction(q));
        }));
    }

    public void upp() {
        uDesc();
    }
}