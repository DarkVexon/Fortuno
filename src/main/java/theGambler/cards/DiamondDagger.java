package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.DiamondDaggerAction;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.DiamondDaggerPower;
import theGambler.wheel.Wheel;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class DiamondDagger extends AbstractFortunoCard {
    public final static String ID = makeID("DiamondDagger");
    // intellij stuff attack, enemy, uncommon, 7, , , , , 

    public DiamondDagger() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new DiamondDaggerAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(DiamondDaggerPower.ID)) {
            cantUseMessage = "I already chose where the Wheel will next land.";
            return false;
        }
        return super.canUse(p, m);
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}