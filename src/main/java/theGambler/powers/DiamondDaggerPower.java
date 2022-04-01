package theGambler.powers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theGambler.FortunoMod.makeID;

public class DiamondDaggerPower extends AbstractEasyPower {
    public static String ID = makeID(DiamondDaggerPower.class.getSimpleName());

    public DiamondDaggerPower(int location) {
        super("Diamond Dagger", PowerType.BUFF, false, AbstractDungeon.player, location);
    }

    @Override
    public void updateDescription() {
        description = "The #gWheel will land on slot #b" + amount + " next.";
    }
}