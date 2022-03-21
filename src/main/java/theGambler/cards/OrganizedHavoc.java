package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.PlayBotCardAction;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class OrganizedHavoc extends AbstractFortunoCard {
    public final static String ID = makeID("OrganizedHavoc");
    // intellij stuff skill, self, common, , , , , , 

    public OrganizedHavoc() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayBotCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng), true));
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {
    }
}