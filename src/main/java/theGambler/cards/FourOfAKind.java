package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToSelf;

public class FourOfAKind extends AbstractFortunoCard {
    public final static String ID = makeID("FourOfAKind");
    // intellij stuff attack, enemy, uncommon, 10, 2, , , 10, 2

    public FourOfAKind() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToSelf(new LambdaPower("Four of a Kind", AbstractPower.PowerType.BUFF, true, p, magicNumber) {
            @Override
            public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
                if (card instanceof FourOfAKind) {
                    return damage + amount;
                }
                return damage;
            }

            @Override
            public void updateDescription() {
                description = "Your #yFour #yOf #yA #yKinds deal #b" + amount + " additional damage this turn.";
            }

            public void atEndOfTurn(boolean isPlayer) {
                if (isPlayer) {
                    this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}