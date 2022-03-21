package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToSelf;

public class Fold extends AbstractFortunoCard {
    public final static String ID = makeID("Fold");
    // intellij stuff skill, self, uncommon, , , , , , 

    public Fold() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedBluePower(p, 2));
        applyToSelf(new DrawCardNextTurnPower(p, 2));
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    public void upp() {
    }
}