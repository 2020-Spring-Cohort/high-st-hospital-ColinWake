public class EmergencyDispatcher extends Employee implements Worker, HealthCareProfessional {

    private boolean onCall;

    private static final int BLOOD_DRAW_AMOUNT = 4;

    private static final int HEALTH_CARE_AMOUNT = 4;

    public EmergencyDispatcher(String name) {
        super(45000, name);
        jobTitle = "Emergency Dispatcher";
        this.onCall = true;
    }

    public boolean isOnCall() {
        return onCall;
    }

    public static int getBloodDrawAmount() {
        return BLOOD_DRAW_AMOUNT;
    }

    public static int getHealthCareAmount() {
        return HEALTH_CARE_AMOUNT;
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(45000);
    }

    @Override
    public void drawBlood(Patient patient) {
        patient.setBloodLevel(patient.getBloodLevel() - getBloodDrawAmount());
    }

    @Override
    public void careForPatient(Patient patient) {
        patient.setHealthLevel(patient.getHealthLevel() + getHealthCareAmount());
    }

    @Override
    public void work() {
        onCall ^= true;
        System.out.println(getJobTitle() + " " + getName() + " is " + (isOnCall() ? "on call" : "not on call") + "!");
    }

    @Override
    public String toString() {
        return super.toString() + " | On call: " + (isWorking() ? "yes" : "no");
    }

    @Override
    public boolean isWorking() {
        return isOnCall();
    }
}
