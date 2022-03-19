package theGambler.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGambler.cards.AbstractFortunoCard;
import theGambler.powers.LambdaPower;

import java.util.Locale;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CasinoChaos extends AbstractFortunoCard {
    public final static String ID = makeID("CasinoChaos");
    // intellij stuff power, self, uncommon, , , , , 5, 

    public CasinoChaos() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Casino Chaos", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (card.rawDescription.toLowerCase(Locale.ROOT).contains("random")) {
                    flash();
                    atb(new DamageRandomEnemyAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a card with 'random' in its text, deal #b" + amount + " damage to a random enemy.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}