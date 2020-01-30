public class Patient {

    private int bloodLevel = 20;

    private int healthLevel = 10;

    private final String name;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBloodLevel() {
        return bloodLevel;
    }

    public void setBloodLevel(int bloodLevel) {
        this.bloodLevel = bloodLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    @Override
    public String toString() {
        return getName() + " | " + "Blood level: " + getBloodLevel() + " | " + "Health level: " + getHealthLevel();
    }
}
