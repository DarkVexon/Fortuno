package theGambler.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.FortunoMod;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class CurioCollection extends AbstractFortunoCard {
    public final static String ID = makeID("CurioCollection");
    // intellij stuff skill, self, rare, , , , , 150, 150

    public CurioCollection() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 150;
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.loseGold(magicNumber);
            }
        });
        float x = this.current_x;
        float y = this.current_y;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(x, y, FortunoMod.returnTrueRandomScreenlessRelic());
            }
        });
    }

    public void upp() {
        FleetingField.fleeting.set(this, false);
        purgeOnUse = true;
        selfRetain = true;
        upgradeMagicNumber(150);
        uDesc();
    }
}