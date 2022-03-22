package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theGambler.actions.EasyXCostAction;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class LuxuriousLifestyle extends AbstractFortunoCard {
    public final static String ID = makeID("LuxuriousLifestyle");
    // intellij stuff skill, all, uncommon, , , , , 7, 3

    public LuxuriousLifestyle() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 0;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            forAllMonstersLiving(q -> {
                applyToEnemyTop(q, new VulnerablePower(q, effect + params[0], false));
                applyToEnemyTop(q, new StrengthPower(q, effect));
            });
            att(new GainGoldAction(magicNumber * effect));
            return true;
        }, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(1);
        uDesc();
    }
}