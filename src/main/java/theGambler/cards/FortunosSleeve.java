package theGambler.cards;

import basemod.AutoAdd;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.actions.RepeatCardAction;
import theGambler.cardmods.DamageBlockUpMod;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.fortunosSleeve;
import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

@AutoAdd.Ignore
public class FortunosSleeve extends AbstractFortunoCard {
    public final static String ID = makeID("FortunosSleeve");
    // intellij stuff skill, self, special, , , , , , 

    public FortunosSleeve() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : fortunosSleeve) {
                    CardModifierManager.addModifier(q, new DamageBlockUpMod(2));
                }
            }
        });
        atb(new SelectCardsAction(fortunosSleeve, "to return to your hand.", (cards) -> {
            AbstractCard tar = cards.get(0);
            att(new RepeatCardAction(AbstractDungeon.getMonsters().getRandomMonster(true), tar.makeSameInstanceOf()));
        }));
        atb(new MakeTempCardInHandAction(this.makeSameInstanceOf(), true));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}