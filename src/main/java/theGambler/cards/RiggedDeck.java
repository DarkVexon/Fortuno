package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.DrawSpecificIDCardAction;
import theGambler.cardmods.DamageBlockUpMod;
import theGambler.util.Wiz;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;
import static theGambler.util.Wiz.att;

public class RiggedDeck extends AbstractFortunoCard {
    public final static String ID = makeID("RiggedDeck");
    // intellij stuff skil, self, common, , , , , , 

    public RiggedDeck() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction("to Rigged Deck.", (cards) -> {
            AbstractCard tar = cards.get(0);
            for (AbstractCard q : Wiz.getAllCardsInCardGroups(true, false)) {
                if (q.cardID.equals(tar.cardID)) {
                    CardModifierManager.addModifier(q, new DamageBlockUpMod(2));
                    q.superFlash();
                }
            }
            att(new DrawSpecificIDCardAction(tar.cardID));
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}