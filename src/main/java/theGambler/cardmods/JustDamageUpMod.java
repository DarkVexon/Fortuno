package theGambler.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class JustDamageUpMod extends AbstractCardModifier {
    int amount;

    public JustDamageUpMod(int amount) {
        this.amount = amount;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + amount;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new JustDamageUpMod(amount);
    }
}
