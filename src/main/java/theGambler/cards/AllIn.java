package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.PlayBotCardAction;
import theGambler.actions.RepeatCardAction;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class AllIn extends AbstractFortunoCard {
    public final static String ID = makeID("AllIn");
    // intellij stuff skill, self, , , , , , 1, 1

    public AllIn() {
        super(ID, 3, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                    this.isDone = true;
                    return;
                }

                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.addToTop(new EmptyDeckShuffleAction());
                    this.isDone = true;
                    return;
                }
            }
        });
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard q = AbstractDungeon.player.drawPile.getBottomCard();
                    AbstractMonster m2 = AbstractDungeon.getMonsters().getRandomMonster(true);
                    for (int i = 0; i < magicNumber; i++) {
                        att(new RepeatCardAction(m2, q));
                    }
                    att(new PlayBotCardAction(m2, false));
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}