package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class SnakeEyes extends AbstractFortunoCard {
    public final static String ID = makeID("SnakeEyes");
    // intellij stuff skill, all_enemy, common, , , , , 3, 1

    public SnakeEyes() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster(true);
                    att(new ApplyPowerAction(q, p, new VulnerablePower(q, 1, false), 1));
                    att(new ApplyPowerAction(q, p, new WeakPower(q, 1, false), 1));
                }
            });
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}