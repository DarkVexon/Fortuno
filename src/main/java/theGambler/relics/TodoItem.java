package theGambler.relics;

import theGambler.FortunoCharacter;

import static theGambler.FortunoMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, FortunoCharacter.Enums.GAMBLER_COLOR);
    }
}
