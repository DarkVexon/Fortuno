package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cardmods.DamageBlockMagicUpMod;

import static theGambler.FortunoMod.ANTE;
import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class HeftyHeart extends AbstractFortunoCard {
    public final static String ID = makeID("HeftyHeart");
    // intellij stuff skill, self, rare, , , , , 7, 3

    public HeftyHeart() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 7;
        tags.add(ANTE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    CardModifierManager.addModifier(q, new DamageBlockMagicUpMod(1));
                    q.superFlash();
                }
            }
        });
        atb(new AddTemporaryHPAction(p, p, magicNumber));
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    public void upp() {
    }
}