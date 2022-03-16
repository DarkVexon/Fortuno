package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class Sideye extends AbstractFortunoCard {
    public final static String ID = makeID("Sideye");
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public Sideye() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, 1)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}