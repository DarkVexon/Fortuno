package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cardmods.DamageBlockUpMod;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class SelfImprovement extends AbstractFortunoCard {
    public final static String ID = makeID("SelfImprovement");
    // intellij stuff skill, self, common, , , , , 4, 2

    public SelfImprovement() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction("to Self Improve.", (cards) -> {
            AbstractCard q = cards.get(0);
            q.selfRetain = true;
            CardModifierManager.addModifier(q, new DamageBlockUpMod(magicNumber));
        }));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}