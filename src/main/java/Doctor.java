public class Doctor extends Employee implements HealthCareProfessional {

    private String specialityArea;

    private static final int BLOOD_DRAW_AMOUNT = 2;

    private static final int HEALTH_CARE_AMOUNT = 3;

    public Doctor(String name, String specialityArea) {
        super(90000, name);
        this.specialityArea = specialityArea;
        jobTitle = specialityArea + " Doctor";
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(90000);
    }

    public String getSpecialityArea() {
        return specialityArea;
    }

    public int getHealthCareAmount() {
        return HEALTH_CARE_AMOUNT;
    }

    public int getBloodDrawAmount() {
        return BLOOD_DRAW_AMOUNT;
    }

    @Override
    public void paySalary() {

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
