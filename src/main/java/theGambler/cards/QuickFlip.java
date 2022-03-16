package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class QuickFlip extends AbstractFortunoCard {
    public final static String ID = makeID("QuickFlip");
    // intellij stuff attack, enemy, common, 8, 3, , , 2, 1

    public QuickFlip() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (int i = 0; i < magicNumber; i++) {
                    if (AbstractDungeon.player.drawPile.size() > i) {
                        AbstractCard target = AbstractDungeon.player.drawPile.getNCardFromTop(AbstractDungeon.player.drawPile.size() - i);
                        target.upgrade();
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}