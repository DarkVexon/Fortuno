package theGambler.patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theGambler.util.Wiz;
import theGambler.wheel.Wheel;

public class WheelRenderPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class ShowQuests {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractDungeon __instance, SpriteBatch sb) {
            if (AbstractDungeon.rs == AbstractDungeon.RenderScene.NORMAL) {
                if (Wiz.isInCombat()) {
                    Wheel.render(sb);
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}