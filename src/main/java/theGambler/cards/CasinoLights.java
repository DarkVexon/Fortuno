package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CasinoLights extends AbstractFortunoCard {
    public final static String ID = makeID("CasinoLights");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public CasinoLights() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, 1)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}