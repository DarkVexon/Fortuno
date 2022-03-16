package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToEnemy;

public class Chatter extends AbstractFortunoCard {
    public final static String ID = makeID("Chatter");
    // intellij stuff attack, enemy, common, 9, 2, , , 2, 1

    public Chatter() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (m.hasPower(StrengthPower.POWER_ID)) {
            if (m.getPower(StrengthPower.POWER_ID).amount > 0) {
                applyToEnemy(m, new StrengthPower(m, -magicNumber));
                if (!m.hasPower(ArtifactPower.POWER_ID))
                    applyToEnemy(m, new GainStrengthPower(m, magicNumber));
            }
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}