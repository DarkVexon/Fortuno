package theGambler.wheel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import theGambler.actions.RepeatCardAction;
import theGambler.util.TexLoader;

import java.util.ArrayList;
import java.util.function.Consumer;

import static theGambler.util.Wiz.atb;
import static theGambler.util.Wiz.att;

public class Wheel {

    public static Texture wheelImg = TexLoader.getTexture("fortunoResources/images/ui/DebugWheel.png");
    public static Texture arrowImg = TexLoader.getTexture("fortunoResources/images/ui/Arrow.png");

    public static ArrayList<ArrayList<AbstractCard>> slots;
    public static ArrayList<Hitbox> hbs;
    public static final int SLOT_NUM = 8;
    public static final int BASE_DMG_BLOCK = 4;

    private static float POSITION_X = Settings.WIDTH / 2F;
    private static float POSITION_Y = (Settings.HEIGHT / 3F) * 2;

    private static float wheelAngle = 0.0F;
    private static float resultAngle = 0.0F;
    private static boolean startSpin = false;
    private static boolean finishSpin = false;
    private static boolean doneSpinning = false;
    private static float animTimer = 2.5F;
    private static float spinVelocity = 200.0F;
    private static float fullVelocity = 750F;
    private static float tmpAngle;

    private static float phase_one_timer = 0.8F;
    private static float phase_two_timer = 1.3F;

    public static void atGameStart() {
        hbs = new ArrayList<>();
        hbs.add(new Hitbox(POSITION_X - 35, POSITION_Y + 90, 76, 76));
        hbs.add(new Hitbox(POSITION_X + 55, POSITION_Y + 50, 76, 76));
        hbs.add(new Hitbox(POSITION_X + 95, POSITION_Y - 35, 76, 76));
        hbs.add(new Hitbox(POSITION_X + 60, POSITION_Y - 120, 76, 76));
        hbs.add(new Hitbox(POSITION_X - 35, POSITION_Y - 150, 76, 76));
        hbs.add(new Hitbox(POSITION_X - 125, POSITION_Y - 120, 76, 76));
        hbs.add(new Hitbox(POSITION_X - 165, POSITION_Y - 30, 76, 76));
        hbs.add(new Hitbox(POSITION_X - 125, POSITION_Y + 50, 76, 76));
    }

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
        resultAngle = (float) result * 45.0F + MathUtils.random(-5.0F, 5.0F);
        startSpin = true;
        animTimer = phase_one_timer;
        spinVelocity = fullVelocity;
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
        sb.draw(wheelImg, POSITION_X - 256.0F, POSITION_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, wheelAngle, 0, 0, 512, 512, false, false);
        sb.draw(arrowImg, POSITION_X - 25F, POSITION_Y - 30F, 25, 115, 50, 230, Settings.scale, Settings.scale, 0, 0, 0, 50, 230, false, false);
        for (Hitbox hb : hbs) {
            hb.render(sb);
        }
    }

    public static void update() {
        if (startSpin) {
            if (animTimer > 0.0F) {
                animTimer -= Gdx.graphics.getDeltaTime();
                wheelAngle += spinVelocity * Gdx.graphics.getDeltaTime();
            } else {
                finishSpin = true;
                animTimer = phase_two_timer;
                startSpin = false;
                tmpAngle = resultAngle;
            }
        } else if (finishSpin) {
            if (animTimer > 0.0F) {
                animTimer -= Gdx.graphics.getDeltaTime();
                if (animTimer < 0.0F) {
                    finishSpin = false;
                    doneSpinning = true;
                }

                wheelAngle = Interpolation.elasticIn.apply(tmpAngle, -180.0F, animTimer / 3.0F);
            }
        }
        if (!finishSpin && !startSpin) {
            for (Hitbox hb : hbs) {
                hb.update();
            }
            for (int i = 0; i < hbs.size(); i++) {
                if (hbs.get(i).hovered) {
                    int idx = (int) ((i + Math.floor((wheelAngle / 45))) % (hbs.size()));

                }
            }
        }
    }
}
