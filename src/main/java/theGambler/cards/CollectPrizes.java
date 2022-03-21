package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.FortunoMod;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class CollectPrizes extends AbstractFortunoCard {
    public final static String ID = makeID("CollectPrizes");
    // intellij stuff attack, all_enemy, rare, 7, , , , , 

    public CollectPrizes() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < FortunoMod.spinsThisCombat; i++) {
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public boolean canAnte() {
        return true;
    }

    @Override
    public void upgrade() {
    }

    public void upp() {
    }
}