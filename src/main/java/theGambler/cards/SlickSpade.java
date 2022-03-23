package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theGambler.actions.FatalRunnableAction;
import theGambler.cards.AbstractFortunoCard;
import theGambler.util.Wiz;
import theGambler.wheel.Wheel;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theGambler.FortunoMod.ANTE;
import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class SlickSpade extends AbstractFortunoCard {
    public final static String ID = makeID("SlickSpade");
    // intellij stuff attack, enemy, rare, 10, 3, , , , 

    public SlickSpade() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FatalRunnableAction(m, new DamageInfo(p, damage, damageTypeForTurn), () -> {
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    ArrayList<AbstractCard> eligible = new ArrayList<>(AbstractDungeon.player.masterDeck.group.stream().filter(q -> q.hasTag(ANTE)).collect(Collectors.toList()));
                    if (!eligible.isEmpty()) {
                        AbstractCard card = Wiz.getRandomItem(eligible);
                        AbstractDungeon.player.masterDeck.removeCard(card);
                        CardCrawlGame.sound.play("CARD_EXHAUST");
                        Wheel.ante(card);
                        AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card));
                    }
                }
            });
        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}