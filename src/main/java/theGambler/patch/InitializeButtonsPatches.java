package theGambler.patch;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theGambler.FortunoCharacter;
import theGambler.wheel.WheelCampOption;

import java.util.ArrayList;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.rooms.CampfireUI",
        method = "initializeButtons"
)
public class InitializeButtonsPatches {
    public static void Postfix(Object __instance) {
        CampfireUI campfire = (CampfireUI) __instance;
        try {
            ArrayList<AbstractCampfireOption> campfireButtons = ReflectionHacks.getPrivate(campfire, CampfireUI.class, "buttons");
            if (AbstractDungeon.player instanceof FortunoCharacter) {
                campfireButtons.add(new WheelCampOption());
            }
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}