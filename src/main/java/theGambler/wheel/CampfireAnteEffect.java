package theGambler.wheel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theGambler.cards.AbstractFortunoCard;

public class CampfireAnteEffect extends AbstractGameEffect {
    private static final float DUR = 1.5F;
    private boolean openedScreen = false;
    private Color screenColor;

    public CampfireAnteEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.updateBlackScreenColor();
        }

        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forPurge) {
            AbstractCard card = (AbstractCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(card);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.topLevelEffects.add(new CampfireSlotInWheelEffect(card));
            isDone = true;
        }

        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;
            CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard q : CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).group) {
                if (q instanceof AbstractFortunoCard) {
                    if (((AbstractFortunoCard) q).canAnte()) {
                        cards.addToTop(q);
                    }
                }
            }
            AbstractDungeon.gridSelectScreen.open(cards, 1, "Choose a card to Ante.", false, false, true, true);
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float) Settings.HEIGHT);
        if (AbstractDungeon.screen == CurrentScreen.GRID) {
            AbstractDungeon.gridSelectScreen.render(sb);
        }

    }

    public void dispose() {
    }
}
