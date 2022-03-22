package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.BlueChipBarrierPower;
import theGambler.powers.LambdaPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class BlueChipBarrier extends AbstractFortunoCard {
    public final static String ID = makeID("BlueChipBarrier");
    // intellij stuff power, self, uncommon, , , , , , 

    public BlueChipBarrier() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BlueChipBarrierPower(1));
        atb(new GainGoldAction(5));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}