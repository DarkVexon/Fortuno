package theGambler.patch;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGambler.actions.SpinWheelAction;

public class SpinOnShufflePatch {
    @SpirePatch(clz = EmptyDeckShuffleAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class ShufflePatchOne {
        public static void Postfix(EmptyDeckShuffleAction __instance) {
            AbstractDungeon.actionManager.addToBottom(new SpinWheelAction());
        }
    }

    @SpirePatch(clz = ShuffleAction.class, method = "update")
    public static class ShufflePatchTwo {
        public static void Postfix(ShuffleAction __instance) {
            boolean b = ReflectionHacks.getPrivate(__instance, ShuffleAction.class, "triggerRelics");
            if (b) {
                AbstractDungeon.actionManager.addToBottom(new SpinWheelAction());
            }
        }
    }

    @SpirePatch(clz = ShuffleAllAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class ShufflePatchThree {
        public static void Postfix(ShuffleAllAction __instance) {
            AbstractDungeon.actionManager.addToBottom(new SpinWheelAction());
        }
    }
}
