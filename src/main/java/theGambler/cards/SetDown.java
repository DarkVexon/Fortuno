package theGambler.cards;

import com.megacrit.cardcrawl.actions.unique.ForethoughtAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class SetDown extends AbstractFortunoCard {
    public final static String ID = makeID("SetDown");
    // intellij stuff skill, self, uncommon, , , , , , 

    public SetDown() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ForethoughtAction(false));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}