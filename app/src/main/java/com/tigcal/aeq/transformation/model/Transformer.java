package com.tigcal.aeq.transformation.model;

import android.support.annotation.NonNull;

public class Transformer implements Comparable<Transformer> {
    private static final String SPECIAL_OPTIMUS = "Optimus Prime";
    private static final String SPECIAL_PREDAKING = "Predaking";

    private String name;
    private int strength;
    private int intelligence;
    private int speed;
    private int endurance;
    private int rank;
    private int courage;
    private int firepower;
    private int skill;
    private int overallRating;
    private boolean alive = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getFirepower() {
        return firepower;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getOverallRating() {
        return strength + intelligence + speed + endurance + firepower;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }

    public boolean isSpecial() {
        return SPECIAL_OPTIMUS.equals(name) || SPECIAL_PREDAKING.equals(name);
    }

    @Override
    public int compareTo(@NonNull Transformer transformer) {
        return this.rank - transformer.rank;
    }
}
