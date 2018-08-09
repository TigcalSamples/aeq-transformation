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
        Transformer autobot = new Transformer();
        autobot.setName("Bumblebee");
        Transformer decepticon = new Transformer();
        decepticon.setName("Predaking");
        int battleResult = BattleManager.startBattle(autobot, decepticon);
        assertEquals(BattleManager.Result.DECEPTICON_WINS, battleResult);
    }

    @Test
    public void courage4Strength3Wins() {
        Transformer autobot = new Transformer();
        autobot.setName("Bumblebee");
        autobot.setCourage(7);
        autobot.setStrength(6);
        Transformer decepticon = new Transformer();
        decepticon.setName("Soundwave");
        decepticon.setCourage(3);
        decepticon.setStrength(2);
        int battleResult = BattleManager.startBattle(autobot, decepticon);
        assertEquals(BattleManager.Result.AUTOBOT_WINS, battleResult);
    }


    @Test
    public void skilledBy3Wins() {
        Transformer autobot = new Transformer();
        autobot.setName("Bumblebee");
        autobot.setSkill(2);
        Transformer decepticon = new Transformer();
        decepticon.setName("Soundwave");
        decepticon.setSkill(6);
        int battleResult = BattleManager.startBattle(autobot, decepticon);
        assertEquals(BattleManager.Result.DECEPTICON_WINS, battleResult);
    }

    @Test
    public void higherOverallRatingWins() {
        Transformer autobot = new Transformer();
        autobot.setName("Bumblebee");
        autobot.setStrength(1);
        autobot.setIntelligence(2);
        autobot.setSpeed(3);
        autobot.setEndurance(4);
        autobot.setFirepower(5);
        Transformer decepticon = new Transformer();
        decepticon.setName("Soundwave");
        decepticon.setStrength(5);
        decepticon.setIntelligence(6);
        decepticon.setSpeed(7);
        decepticon.setEndurance(8);
        decepticon.setFirepower(9);
        int battleResult = BattleManager.startBattle(autobot, decepticon);
        assertEquals(BattleManager.Result.DECEPTICON_WINS, battleResult);
    }

    @Test
    public void tiedBattle() {
        Transformer autobot = new Transformer();
        autobot.setName("Bumblebee");
        autobot.setStrength(1);
        autobot.setIntelligence(2);
        autobot.setSpeed(3);
        autobot.setEndurance(4);
        autobot.setFirepower(5);
        Transformer decepticon = new Transformer();
        decepticon.setName("Soundwave");
        decepticon.setStrength(1);
        decepticon.setIntelligence(2);
        decepticon.setSpeed(3);
        decepticon.setEndurance(4);
        decepticon.setFirepower(5);
        int battleResult = BattleManager.startBattle(autobot, decepticon);
        assertEquals(BattleManager.Result.DRAW, battleResult);
    }


}
