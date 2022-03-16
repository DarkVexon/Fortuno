package theGambler.cards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class DoubleOrNothing extends AbstractFortunoCard {
    public final static String ID = makeID("DoubleOrNothing");
    // intellij stuff , self, uncommon, , , , , 1, 1

    public DoubleOrNothing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new ScryAction(magicNumber));
        atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}