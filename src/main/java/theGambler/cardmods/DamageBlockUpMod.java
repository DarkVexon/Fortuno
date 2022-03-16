package theGambler.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageBlockUpMod extends AbstractCardModifier {
    int amount;

    public DamageBlockUpMod(int amount) {
        this.amount = amount;
    }

    @Override
    public float modifyBlock(float block, AbstractCard card) {
        return block + amount;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + amount;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageBlockUpMod(amount);
    }
}
