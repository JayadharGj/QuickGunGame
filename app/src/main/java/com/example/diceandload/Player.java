package com.example.diceandload;

import java.util.ArrayList;

class Player {
    private int Id;
    private int rowScope;
    private ArrayList<Integer> gunMans;
    private int gunMansAliveCount;

    Player(int id, int scope, ArrayList<Integer> gunMans) {
        Id = id;
       rowScope = scope;
        this.gunMans = gunMans;
        gunMansAliveCount = 6;
    }

    void initGunMans(int val) {
        for (int i = 0; i < rowScope; i++) {
            gunMans.add(val);
        }
    }

    public int getId() {
        return Id;
    }

    void levelUp(int i) {
        gunMans.set(i, gunMans.get(i) + 1);
    }

    void kill(int i) {
        if (!isGunManDead(i)) {
            gunMans.set(i, 0);
            gunMansAliveCount--;
        }
    }

    boolean isPlayerDead() {
        return gunMansAliveCount <= 0;
    }

    boolean isGunManDead(int i) {
        return gunMans.get(i) == 0;
    }

    public int getRowScope() {
        return rowScope;
    }

    ArrayList<Integer> getGunMans() {
        return gunMans;
    }
}
