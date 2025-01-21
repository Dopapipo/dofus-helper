
package fr.pantheonsorbonne.entity;

public class Plant {
    private String type;
    private int growthTime; // in hours
    private boolean isDead;

    public Plant(String type, int growthTime) {
        this.type = type;
        this.growthTime = growthTime;
        this.isDead = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(int growthTime) {
        this.growthTime = growthTime;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
}
