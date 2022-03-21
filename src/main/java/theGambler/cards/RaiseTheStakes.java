package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.cardmods.DamageBlockUpMod;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class RaiseTheStakes extends AbstractFortunoCard {
    public final static String ID = makeID("RaiseTheStakes");
    // intellij stuff skill, self, uncommon, , , , , 2, -1

    public RaiseTheStakes() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    CardModifierManager.addModifier(q, new DamageBlockUpMod(2));
                    q.superFlash();
                }
            }
        });
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}