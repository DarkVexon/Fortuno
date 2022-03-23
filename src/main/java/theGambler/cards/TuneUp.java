package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cardmods.DamageBlockUpMod;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class TuneUp extends AbstractFortunoCard {
    public final static String ID = makeID("TuneUp");
    // intellij stuff skill, self, uncommon, , , , , 100, -25

    public TuneUp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 100;
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.loseGold(magicNumber);
            }
        });
        if (upgraded) {
            atb(new DrawCardAction(1));
        }
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    CardModifierManager.addModifier(q, new DamageBlockUpMod(2));
                    if (StSLib.getMasterDeckEquivalent(q) != null) {
                        CardModifierManager.addModifier(StSLib.getMasterDeckEquivalent(q), new DamageBlockUpMod(2));
                    }
                }
            }
        });
    }

    public void upp() {
        selfRetain = true;
        upgradeMagicNumber(-25);
        uDesc();
    }
}