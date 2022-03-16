package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class OlFaithful extends AbstractFortunoCard {
    public final static String ID = makeID("OlFaithful");
    // intellij stuff attack, enemy, basic, 8, , , , 1, 

    public OlFaithful() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (isHit()) {
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        }
    }

    public void upp() {
    }

    @Override
    public boolean canAnte() {
        return true;
    }
}