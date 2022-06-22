package theGambler.cards;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.wheel.Wheel;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.atb;

public class OnTheHouse extends AbstractFortunoCard {
    public final static String ID = makeID("OnTheHouse");
    // intellij stuff attack, enemy, uncommon, 4, , , , 3, 1

    public OnTheHouse() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 3;
    }

    private static AbstractGameAction.AttackEffect getRandomAttackFX() {
        byte result = (byte) AbstractDungeon.cardRandomRng.random(11);
        return AbstractGameAction.AttackEffect.values()[result];
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, getRandomAttackFX());
        }
        atb(new SelectCardsInHandAction("to Ante.", (cards) -> {
            AbstractCard q = cards.get(0);
            AbstractCard r = StSLib.getMasterDeckEquivalent(q);
            if (r != null) {
                AbstractDungeon.player.masterDeck.removeCard(r);
            }
            Wheel.ante(q);
            AbstractDungeon.handCardSelectScreen.selectedCards.removeCard(q);
        }));
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        upp();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}