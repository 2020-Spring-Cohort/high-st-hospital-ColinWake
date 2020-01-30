public class Doctor extends Employee implements HealthCareProfessional {

    private static final int BLOOD_DRAW_AMOUNT = 2;

    private static final int HEALTH_CARE_AMOUNT = 3;

    public Doctor(String name, String specialityArea) {
        super(90000, name);
        jobTitle = specialityArea + " Doctor";
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(90000);
    }

    public int getHealthCareAmount() {
        return HEALTH_CARE_AMOUNT;
    }

    public int getBloodDrawAmount() {
        return BLOOD_DRAW_AMOUNT;
    }

    @Override
    public void drawBlood(Patient patient) {
        patient.setBloodLevel(patient.getBloodLevel() - getBloodDrawAmount());
    }

    @Override
    public void careForPatient(Patient patient) {
        patient.setHealthLevel(patient.getHealthLevel() + getHealthCareAmount());
    }
}
