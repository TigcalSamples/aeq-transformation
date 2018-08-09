package com.tigcal.aeq.transformation.util;

import com.tigcal.aeq.transformation.model.Transformer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BattleManagerTest {

    @Test
    public void bothSpecialTransformersEndBattle() {
        Transformer optimus = new Transformer();
        optimus.setName("Optimus Prime");
        Transformer predaking = new Transformer();
        predaking.setName("Predaking");
        int battleResult = BattleManager.startBattle(optimus, predaking);
        assertEquals(BattleManager.Result.END, battleResult);
    }
}
