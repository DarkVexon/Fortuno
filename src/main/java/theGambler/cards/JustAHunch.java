package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class JustAHunch extends AbstractFortunoCard {
    public final static String ID = makeID("JustAHunch");
    // intellij stuff power, self, uncommon, , , , , , 

    public JustAHunch() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Just A Hunch", AbstractPower.PowerType.BUFF, false, p, 1) {

            @Override
            public void atStartOfTurnPostDraw() {
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        if (!AbstractDungeon.player.drawPile.isEmpty()) {
                            flash();
                            AbstractCard targeted = AbstractDungeon.player.drawPile.getBottomCard();
                            if (targeted.type == CardType.ATTACK) {
                                att(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
                            } else {
                                att(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
                            }
                            att(new ExhaustSpecificCardAction(targeted, AbstractDungeon.player.exhaustPile));
                        }
                    }
                });
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, #yExhaust the bottom card of your draw pile. If it was an Attack, gain #b" + amount + " #yStrength. NL Otherwise, gain #b" + amount + " #yDexterity.";
            }
        });
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}