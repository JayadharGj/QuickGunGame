package com.example.diceandload;

import java.util.ArrayList;

class Player {
    private int Id;
    private int rowScope;
    private ArrayList<Integer> gunMans;

    Player(int id, int scope, ArrayList<Integer> gunMans) {
        Id = id;
        scope = rowScope;
        this.gunMans = gunMans;
    }

    void initGunMans(final int val) {
        gunMans.forEach((n) -> n = val);
    }

    public int getId() {
        return Id;
    }

    void levelUp(int i) {
        gunMans.set(i, gunMans.get(i) + 1);
    }

    void kill(int i) {
        gunMans.set(i, 0);
    }

    public int getRowScope() {
        return rowScope;
    }

    public ArrayList<Integer> getGunMans() {
        return gunMans;
    }
}
