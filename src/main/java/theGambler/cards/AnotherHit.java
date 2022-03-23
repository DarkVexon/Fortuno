package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.PlayBotCardAction;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class AnotherHit extends AbstractFortunoCard {
    public final static String ID = makeID("AnotherHit");
    // intellij stuff attack, enemy, uncommon, 12, 4, , , , 

    public AnotherHit() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard q = AbstractDungeon.player.drawPile.getBottomCard();
                    if (q.type == CardType.ATTACK) {
                        att(new PlayBotCardAction(m, false));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}