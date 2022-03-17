package theGambler.wheel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.RepeatCardAction;

import java.util.ArrayList;
import java.util.function.Consumer;

import static theGambler.util.Wiz.atb;
import static theGambler.util.Wiz.att;

public class Wheel {
    public static ArrayList<ArrayList<AbstractCard>> slots;
    public static final int SLOT_NUM = 8;
    public static final int BASE_DMG_BLOCK = 4;

    private static float POSITION_X = 150F;
    private static float POSITION_Y = 150F;

    private static float wheelAngle = 0.0F;
    private static float resultAngle = 0.0F;
    private static boolean startSpin = false;
    private static boolean finishSpin = false;
    private static boolean doneSpinning = false;
    private static float animTimer = 2.5F;
    private static float spinVelocity = 200.0F;
    private static float tmpAngle;

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
        resultAngle = (float) result * 60.0F + MathUtils.random(-10.0F, 10.0F);
        startSpin = true;
        animTimer = 2.0F;
        spinVelocity = 1500.0F;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = Wheel.doneSpinning;
            }
        });

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
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
        });
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
        if (startSpin) {
            if (animTimer > 0.0F) {
                animTimer -= Gdx.graphics.getDeltaTime();
                wheelAngle += spinVelocity * Gdx.graphics.getDeltaTime();
            } else {
                finishSpin = true;
                animTimer = 3.0F;
                startSpin = false;
                tmpAngle = resultAngle;
            }
        } else if (finishSpin) {
            if (animTimer > 0.0F) {
                animTimer -= Gdx.graphics.getDeltaTime();
                if (animTimer < 0.0F) {
                    animTimer = 1.0F;
                    finishSpin = false;
                    doneSpinning = true;
                }

                wheelAngle = Interpolation.elasticIn.apply(tmpAngle, -180.0F, animTimer / 3.0F);
            }
        }
    }
}
