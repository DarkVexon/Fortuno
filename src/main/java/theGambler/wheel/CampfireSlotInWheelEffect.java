package theGambler.wheel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CampfireSlotInWheelEffect extends AbstractGameEffect {
    private static final float DUR = 1.5F;
    public AbstractCard targetCard;

    public CampfireSlotInWheelEffect(AbstractCard targetCard) {
        this.targetCard = targetCard;
        this.duration = 1.5F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        Wheel.updateInCamp(this);
    }


    public void render(SpriteBatch sb) {
        Wheel.renderInCamp(sb);
    }

    public void dispose() {
    }
}
