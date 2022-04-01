package theGambler.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theGambler.cards.AbstractFortunoCard;

import static theGambler.FortunoMod.makeID;
import static theGambler.util.Wiz.*;

public class FortunoCoin extends AbstractFortunoCard implements OnChangeActCard {
    public final static String ID = makeID("FortunoCoin");
    // intellij stuff skill, none, rare, , , , , , 100

    public FortunoCoin() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 300;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "Wait, this is just a certificate!";
        return false;
    }

    @Override
    public void onChangeAct() {
        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
        AbstractDungeon.player.gainGold(magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(100);
    }
}