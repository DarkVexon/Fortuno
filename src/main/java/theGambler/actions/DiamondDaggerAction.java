package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theGambler.wheel.Wheel;

public class DiamondDaggerAction extends AbstractGameAction {
    private boolean hasDone = false;

    @Override
    public void update() {
        if (!hasDone) {
            Wheel.choosingForDiamondDagger = true;
            hasDone = true;
        }
    }
}
