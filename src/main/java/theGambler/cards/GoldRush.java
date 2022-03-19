package theGambler.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class GoldRush extends AbstractFortunoCard {
    public final static String ID = makeID("GoldRush");
    // intellij stuff attack, all_enemy, uncommon, 17, 5, , , , 

    public GoldRush() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 17;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster q = AbstractDungeon.getRandomMonster();
                att(new DamageCallbackAction(q, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.FIRE, (result) -> {
                   AbstractDungeon.player.gainGold(result);
                }));
            }
        });
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
        upgradeDamage(5);
    }
}