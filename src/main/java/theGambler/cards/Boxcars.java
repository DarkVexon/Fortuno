package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class Boxcars extends AbstractFortunoCard implements OnSpinWheelCard {
    public final static String ID = makeID("Boxcars");
    // intellij stuff attack, enemy, uncommon, 6, 2, , , 6, 2

    public Boxcars() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void onSpinWheel() {
        addToBot(new ModifyDamageAction(this.uuid, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}