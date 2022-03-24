package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.BottomToTopAction;

import java.util.ArrayList;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class CalledIt extends AbstractFortunoCard {
    public final static String ID = makeID("CalledIt");
    // intellij stuff attack, enemy, common, 8, 2, , , , 

    public CalledIt() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public static CardType tar = CardType.STATUS;

    public void use(AbstractPlayer p, AbstractMonster m) {
        tar = CardType.STATUS;
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        cardsList.add(new EasyModalChoiceCard("Attack", "Guess the card at the bottom of your draw pile is an Attack.", () -> tar = CardType.ATTACK));
        cardsList.add(new EasyModalChoiceCard("Skill", "Guess the card at the bottom of your draw pile is a Skill.", () -> tar = CardType.SKILL));
        cardsList.add(new EasyModalChoiceCard("Power", "Guess the card at the bottom of your draw pile is a Power.", () -> tar = CardType.POWER));
        atb(new BottomToTopAction(p.drawPile));
        atb(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!DrawCardAction.drawnCards.isEmpty()) {
                    AbstractCard q = DrawCardAction.drawnCards.get(0);
                    if (q.type == tar) {
                        dmgTop(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                    }
                }
            }
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}