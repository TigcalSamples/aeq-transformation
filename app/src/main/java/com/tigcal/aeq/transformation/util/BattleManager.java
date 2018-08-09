package com.tigcal.aeq.transformation.util;

import android.support.annotation.IntDef;

import com.tigcal.aeq.transformation.model.Transformer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BattleManager {

    @IntDef({Result.END, Result.DRAW, Result.AUTOBOT_WINS, Result.DECEPTICON_WINS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
        int END = 0;
        int DRAW = 1;
        int AUTOBOT_WINS = 2;
        int DECEPTICON_WINS = 3;
    }

    /**
     * Returns the results of the battle between two transformers
     *
     * @return BattleManager.Result
     */
    public static @Result
    int startBattle(Transformer autobot, Transformer decepticon) {
        if (autobot.isSpecial() && decepticon.isSpecial()) {
            return Result.END;
        } else if (autobot.isSpecial()) {
            return Result.AUTOBOT_WINS;
        } else if (decepticon.isSpecial()) {
            return Result.DECEPTICON_WINS;
        } else if (autobot.getCourage() - decepticon.getCourage() >= 4
                && autobot.getStrength() - decepticon.getStrength() >= 3) {
            return Result.AUTOBOT_WINS;
        } else if (decepticon.getCourage() - autobot.getCourage() >= 4
                && decepticon.getStrength() - autobot.getStrength() >= 3) {
            return Result.DECEPTICON_WINS;
        }

        return Result.DRAW;//FIXME
    }
}
