package theGambler.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public interface OnSpinWheelPower {
    void onSpinWheel(ArrayList<AbstractCard> results, boolean isRed);
}
