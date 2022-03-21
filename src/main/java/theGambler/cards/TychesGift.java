package theGambler.cards;

import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class TychesGift extends AbstractFortunoCard {
    public final static String ID = makeID("TychesGift");
    // intellij stuff skill, self, uncommon, , , 2, 1, , 

    public TychesGift() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = AbstractDungeon.player.hand.group.size();
        atb(new CalculatedGambleAction(false));
        for (int i = 0; i < x; i++) {
            blck();
        }
    }

    public void upp() {
        upgradeBlock(1);
    }
}