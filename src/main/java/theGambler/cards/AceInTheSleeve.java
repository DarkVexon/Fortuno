package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.FortunoMod;

import static theGambler.FortunoMod.fortunosSleeve;
import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class AceInTheSleeve extends AbstractFortunoCard {
    public final static String ID = makeID("AceInTheSleeve");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AceInTheSleeve() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    fortunosSleeve.add(q.makeSameInstanceOf());
                }
                p.hand.group.clear();
            }
        });
        atb(new MakeTempCardInHandAction(new FortunosSleeve()));
        atb(new DrawCardAction(magicNumber));
    }


    public void upp() {
        upgradeMagicNumber(1);
    }
}