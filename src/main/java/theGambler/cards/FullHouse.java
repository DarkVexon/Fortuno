package theGambler.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cardmods.DamageBlockUpMod;
import theGambler.powers.LambdaPower;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.applyToSelf;
import static theGambler.util.Wiz.atb;

public class FullHouse extends AbstractFortunoCard {
    public final static String ID = makeID("FullHouse");
    // intellij stuff power, self, rare, , , , , 4, 2

    public FullHouse() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Full House", AbstractPower.PowerType.BUFF, false, p, magicNumber) {

            @Override
            public void atStartOfTurnPostDraw() {
                int x = this.amount;
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        ArrayList<String> dupesFound = new ArrayList<>();
                        ArrayList<AbstractCard> dupes = new ArrayList<>();
                        for (AbstractCard q : AbstractDungeon.player.hand.group) {
                            if (!dupesFound.contains(q.cardID)) {
                                if (AbstractDungeon.player.hand.group.stream().anyMatch(d -> d.cardID.equals(q.cardID) && d != q)) {
                                    dupesFound.add(q.cardID);
                                    dupes.addAll(AbstractDungeon.player.hand.group.stream().filter(d -> d.cardID.equals(q.cardID)).collect(Collectors.toList()));
                                }
                            }
                        }
                        for (AbstractCard q : dupes) {
                            CardModifierManager.addModifier(q, new DamageBlockUpMod(x));
                            q.superFlash();
                        }
                    }
                });
            }

            @Override
            public void updateDescription() {
                description = "Whenever you draw more than one of the same card at the start of your turn, give them #b" + amount + " increased damage and Block.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}