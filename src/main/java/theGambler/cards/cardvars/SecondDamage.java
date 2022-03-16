package theGambler.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;

public class SecondDamage extends DynamicVariable {

    @Override
    public String key() {
        return makeID("sd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).isSecondDamageModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractFortunoCard) {
            ((AbstractFortunoCard) card).isSecondDamageModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).secondDamage;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).baseSecondDamage;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractFortunoCard) {
            return ((AbstractFortunoCard) card).upgradedSecondDamage;
        }
        return false;
    }
}