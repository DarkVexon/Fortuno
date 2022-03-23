package theGambler.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageBlockMagicUpMod extends AbstractCardModifier {
    int amount;

    public DamageBlockMagicUpMod(int amount) {
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
    public void onApplyPowers(AbstractCard card) {
        int realBase = card.baseMagicNumber;
        card.baseMagicNumber += amount;
        super.onApplyPowers(card);
        card.baseMagicNumber = realBase;
        card.isMagicNumberModified = card.magicNumber != card.baseMagicNumber;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageBlockMagicUpMod(amount);
    }
}
