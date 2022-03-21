package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class TinyTells extends AbstractFortunoCard {
    public final static String ID = makeID("TinyTells");
    // intellij stuff attack, enemy, common, 3, 2, , , , 

    public TinyTells() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractCard q = AbstractDungeon.player.drawPile.getBottomCard();
            if (q.type == CardType.SKILL) {
                applyToEnemy(m, new WeakPower(m, 1, false));
            }
        }
    }

    public void upp() {
        upgradeDamage(2);
    }
}