package theGambler.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theGambler.wheel.Wheel;

import java.util.function.Consumer;

public class SpinWheelAction extends AbstractGameAction {
    private Consumer<AbstractCard> postSpin;

    public SpinWheelAction() {
        this.postSpin = (c -> {

        });
    }

    public SpinWheelAction(Consumer<AbstractCard> consum) {
        this.postSpin = consum;
    }

    @Override
    public void update() {
        isDone = true;
        Wheel.spin(postSpin);
    }
}
