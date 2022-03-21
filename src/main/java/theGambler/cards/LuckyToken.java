package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class LuckyToken extends AbstractFortunoCard implements OnSpinWheelCard {
    public final static String ID = makeID("LuckyToken");
    // intellij stuff attack, enemy, common, 4, 2, , , , 

    public LuckyToken() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void onSpinWheel() {
        atb(new DiscardToHandAction(this));
    }

    public void upp() {
        upgradeDamage(2);
    }
}