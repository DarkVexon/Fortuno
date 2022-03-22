package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;
import static theGambler.util.Wiz.att;

public class Jackpot extends AbstractFortunoCard {
    public final static String ID = makeID("Jackpot");
    // intellij stuff attack, all_enemy, rare, 7, , , , , 

    public Jackpot() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        baseSecondDamage = secondDamage = 777;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!isHit()) {
            atb(new AttackDamageRandomEnemyAction(this));
        } else {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    ArrayList<AbstractMonster> valid = new ArrayList<>();
                    for (AbstractMonster q : AbstractDungeon.getMonsters().monsters) {
                        if (q.type != AbstractMonster.EnemyType.BOSS && !q.isDying && !q.isDead) {
                            valid.add(q);
                        }
                    }
                    if (!valid.isEmpty()) {
                        AbstractMonster tar = valid.get(AbstractDungeon.cardRandomRng.random(valid.size() - 1));
                        att(new DamageAction(tar, new DamageInfo(p, secondDamage, damageTypeForTurn), AttackEffect.FIRE));
                    }
                }
            });
        }
    }

    @Override
    public boolean canAnte() {
        return super.canAnte();
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