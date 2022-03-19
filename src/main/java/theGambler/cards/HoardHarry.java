package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class HoardHarry extends AbstractFortunoCard {
    public final static String ID = makeID("HoardHarry");
    // intellij stuff attack, enemy, uncommon, 18, 4, 12, 3, , 

    public HoardHarry() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;
        baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (strengthCheck()) {
            blck();
        }
    }

    private static boolean strengthCheck() {
        if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)) {
            int x = AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            if (x > 3) {
                return true;
            }
        }
        return false;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = strengthCheck() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(4);
        upgradeBlock(3);
    }
}