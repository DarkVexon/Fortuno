package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class HeavyHit extends AbstractFortunoCard implements OnSpinWheelCard {
    public final static String ID = makeID("HeavyHit");
    // intellij stuff attack, enemy, uncommon, 24, 6, , , , 

    public HeavyHit() {
        super(ID, 4, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 24;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void onSpinWheel() {
        updateCost(-1);
    }

    public void upp() {
        upgradeDamage(6);
    }
}