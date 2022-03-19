package theGambler.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class ScrapShield extends AbstractFortunoCard {
    public final static String ID = makeID("ScrapShield");
    // intellij stuff skill, self, common, , , 7, 2, , 

    public ScrapShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.drawPile.size() >= 10) {
            blck();
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.player.drawPile.size() >= 10 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
    }
}