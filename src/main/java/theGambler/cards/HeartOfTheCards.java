package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cardmods.JustDamageUpMod;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class HeartOfTheCards extends AbstractFortunoCard {
    public final static String ID = makeID("HeartOfTheCards");
    // intellij stuff skill, self, uncommon, , , , , 8, 4

    public HeartOfTheCards() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.drawPile.isEmpty()) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard target = AbstractDungeon.player.drawPile.getBottomCard();
                    CardModifierManager.addModifier(target, new JustDamageUpMod(magicNumber));
                    AbstractDungeon.player.drawPile.moveToHand(target);
                }
            });
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.drawPile.isEmpty()) {
            return false;
        }
        return super.canUse(p, m);
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}