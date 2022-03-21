package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class PullFromNothing extends AbstractFortunoCard {
    public final static String ID = makeID("PullFromNothing");
    // intellij stuff attack, enemy, rare, 15, 1, , , , 

    public PullFromNothing() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new ExhumeAction(upgraded));
    }

    public void upp() {
        upgradeDamage(1);
        uDesc();
    }
}