package theGambler.wheel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.RepeatCardAction;

import java.util.ArrayList;
import java.util.function.Consumer;

import static theGambler.util.Wiz.att;

public class Wheel {
    public static ArrayList<ArrayList<AbstractCard>> slots;
    public static final int SLOT_NUM = 8;
    public static final int BASE_DMG_BLOCK = 4;

    public static void init() {
        slots = new ArrayList<>();
        for (int i = 0; i < SLOT_NUM; i++) {
            slots.add(new ArrayList<>());
        }
    }

    public static void spin() {
        spin(q -> {

        });
    }

    public static void spin(Consumer<AbstractCard> postSpin) {
        int result = AbstractDungeon.cardRandomRng.random(slots.size() - 1);
        if (slots.get(result).isEmpty()) {
            boolean red = result % 2 == 0;
            if (red) {
                att(new DamageRandomEnemyAction(new DamageInfo((null), BASE_DMG_BLOCK, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            } else {
                att(new GainBlockAction(AbstractDungeon.player, BASE_DMG_BLOCK));
            }
        } else {
            for (AbstractCard q : slots.get(result)) {
                postSpin.accept(q);
                AbstractDungeon.actionManager.addToTop(new RepeatCardAction(q));
            }
        }
    }

    public static void ante(AbstractCard card, int slot) {
        slots.get(slot).add(card.makeStatEquivalentCopy());
    }

    public static void ante(AbstractCard card) {
        int result = AbstractDungeon.cardRandomRng.random(slots.size() - 1);
        slots.get(result).add(card.makeStatEquivalentCopy());
    }

    public static void render(SpriteBatch sb) {

    }

    public static void update() {

    }
}
