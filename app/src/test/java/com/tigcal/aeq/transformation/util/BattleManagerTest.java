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

    @Test
    public void specialAutobotWins() {
        Transformer optimus = new Transformer();
        optimus.setName("Optimus Prime");
        Transformer decepticon = new Transformer();
        decepticon.setName("Soundwave");
        int battleResult = BattleManager.startBattle(optimus, decepticon);
        assertEquals(BattleManager.Result.AUTOBOT_WINS, battleResult);
    }

    @Test
    public void specialDecepticonWins() {
        Transformer optimus = new Transformer();
        optimus.setName("Bumblebee");
        Transformer decepticon = new Transformer();
        decepticon.setName("Predaking");
        int battleResult = BattleManager.startBattle(optimus, decepticon);
        assertEquals(BattleManager.Result.DECEPTICON_WINS, battleResult);
    }
}
