package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class PokerFace extends AbstractFortunoCard {
    public final static String ID = makeID("PokerFace");
    // intellij stuff skill, all, uncommon, , , , , , 

    public PokerFace() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int maxStrength = upgraded ? 1 : 0;
        forAllMonstersLiving((q) -> applyToEnemy(q, new StrengthPower(q, -1)));
        for (AbstractMonster q : AbstractDungeon.getMonsters().monsters) {
            if (!q.isDead && !q.isDying) {
                maxStrength += 1;
            }
        }
        applyToSelf(new StrengthPower(p, maxStrength));
    }

    public void upp() {
        uDesc();
    }
}