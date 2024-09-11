package medal;

public class Medal {
    private String country;
    private int gold;
    private int silver;
    private int bronze;

    // Constructor
    public Medal(String country, int gold, int silver, int bronze) {
        this.country = country;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    // Getters and Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    @Override
    public String toString() {
        return "Medal{" +
                "country='" + country + '\'' +
                ", gold=" + gold +
                ", silver=" + silver +
                ", bronze=" + bronze +
                '}';
    }
}
