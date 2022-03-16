package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theGambler.wheel.Wheel;

public class SpinWheelAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        Wheel.spin();
    }
}
