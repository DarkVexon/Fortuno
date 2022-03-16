package theGambler.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;

public class SecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("m2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractFortunoCard) {
            ((AbstractFortunoCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).upgradedSecondMagic;
        }
        return false;
    }
}