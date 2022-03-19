package theGambler.wheel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import theGambler.actions.RepeatCardAction;
import theGambler.powers.OnSpinWheelPower;
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

    private static float CAMPFIRE_POSITION_X = Settings.WIDTH / 2F;
    private static float CAMPFIRE_POSITION_Y = (Settings.HEIGHT / 3F) * 2;

    private static float wheelAngle = 0.0F;
    private static float resultAngle = 0.0F;
    private static boolean startSpin = false;
    private static boolean finishSpin = false;
    private static boolean doneSpinning = false;
    private static float animTimer = 2.5F;
    private static float spinVelocity = 200.0F;
    private static float fullVelocity = 300F;
    private static float tmpAngle;

    private static float phase_one_timer = 0.8F;
    private static float phase_two_timer = 1.3F;

    private static float STORED_CARD_SCALE = 0.5F;

    private static float CARD_RENDER_HEIGHT = ((Settings.HEIGHT / 3F) * 2) + 50;

    public static final Vector2[] cardPositions = {
            new Vector2(Settings.WIDTH / 2F + (400 * Settings.xScale), CARD_RENDER_HEIGHT),
            new Vector2(Settings.WIDTH / 2F + (500 * Settings.xScale), CARD_RENDER_HEIGHT),
            new Vector2(Settings.WIDTH / 2F + (600 * Settings.xScale), CARD_RENDER_HEIGHT)
    };

    private static float CAMPFIRE_CARD_RENDER_HEIGHT = ((Settings.HEIGHT / 3F) * 2) + 50;

    public static final Vector2[] campCardPositions = {
            new Vector2(218f * Settings.xScale, CARD_RENDER_HEIGHT),
            new Vector2(293f * Settings.xScale, CARD_RENDER_HEIGHT),
            new Vector2(368f * Settings.xScale, CARD_RENDER_HEIGHT)
    };

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

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnSpinWheelPower) {
                ((OnSpinWheelPower) p).onSpinWheel();
            }
        }
    }

    public static void ante(AbstractCard card, int slot) {
        AbstractCard q = card.makeStatEquivalentCopy();
        q.stopGlowing();
        q.resetAttributes();
        q.current_x = cardPositions[slots.get(slot).size()].x;
        q.current_y = cardPositions[slots.get(slot).size()].y;
        q.target_x = q.current_x;
        q.target_y = q.current_y;
        q.drawScale = STORED_CARD_SCALE;
        q.targetDrawScale = q.drawScale;
        slots.get(slot).add(card);
    }

    public static void render(SpriteBatch sb) {
        sb.draw(wheelImg, POSITION_X - 256.0F, POSITION_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, wheelAngle, 0, 0, 512, 512, false, false);
        sb.draw(arrowImg, POSITION_X - 25F, POSITION_Y - 30F, 25, 115, 50, 230, Settings.scale, Settings.scale, 0, 0, 0, 50, 230, false, false);
        for (int i = 0; i < slots.size(); i++) {
            if (hbs.get((int) ((i + Math.floor((wheelAngle / 45))) % (hbs.size()))).hovered) {
                for (AbstractCard q : slots.get(i)) {
                    q.render(sb);
                }
            }
        }
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
            for (ArrayList<AbstractCard> q : slots) {
                for (AbstractCard c : q) {
                    c.target_x = cardPositions[q.indexOf(c)].x;
                    c.target_y = cardPositions[q.indexOf(c)].y;
                    c.targetDrawScale = STORED_CARD_SCALE;
                    c.update();
                }
            }
        }
    }

    public static void renderInCamp(SpriteBatch sb) {
        sb.draw(wheelImg, POSITION_X - 256.0F, POSITION_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, wheelAngle, 0, 0, 512, 512, false, false);
        //sb.draw(arrowImg, POSITION_X - 25F, POSITION_Y - 30F, 25, 115, 50, 230, Settings.scale, Settings.scale, 0, 0, 0, 50, 230, false, false);
        for (int i = 0; i < slots.size(); i++) {
            if (hbs.get((int) ((i + Math.floor((wheelAngle / 45))) % (hbs.size()))).hovered) {
                for (AbstractCard q : slots.get(i)) {
                    q.render(sb);
                }
            }
        }
        for (Hitbox hb : hbs) {
            hb.render(sb);
        }
    }

    public static void updateInCamp(CampfireSlotInWheelEffect e) {
        for (Hitbox hb : hbs) {
            hb.update();
        }
        for (int i = 0; i < hbs.size(); i++) {
            if (hbs.get(i).hovered) {
                int idx = (int) ((i + Math.floor((wheelAngle / 45))) % (hbs.size()));
                TipHelper.renderGenericTip((float) InputHelper.mX + 60.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale, "Slot " + String.valueOf(idx), "Ante this card in this slot.");
                if (InputHelper.justClickedLeft) {
                    ante(e.targetCard, idx);
                    e.isDone = true;
                    AbstractRoom.waitTimer = 0.0F;
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                    ((RestRoom) AbstractDungeon.getCurrRoom()).cutFireSound();
                    CardCrawlGame.sound.play("CARD_EXHAUST");
                }
            }
        }
    }
}
