package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class StarchipStalwart extends AbstractFortunoCard {
    public final static String ID = makeID("StarchipStalwart");
    // intellij stuff skill, self, common, , , 7, 1, 3, 1

    public StarchipStalwart() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (isHit()) {
            forAllMonstersLiving(q -> applyToEnemy(q, new WeakPower(q, 1, false)));
        }
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