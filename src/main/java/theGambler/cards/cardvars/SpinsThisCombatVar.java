package theGambler.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theGambler.FortunoMod;

public class SpinsThisCombatVar extends DynamicVariable {
    @Override
    public String key() {
        return FortunoMod.makeID("spins");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return value(card) != 0;
    }

    @Override
    public int value(AbstractCard card) {
        return FortunoMod.spinsThisCombat;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
