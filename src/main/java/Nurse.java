import java.util.ArrayList;
import java.util.List;

public class Nurse extends Employee implements MedicalProfessional {

    private static final int BLOOD_DRAW_AMOUNT = 3;

    private static final int HEALTH_CARE_AMOUNT = 2;

    private final List<Patient> patientsUnderCare = new ArrayList<>();

    public Nurse(String name) {
        super(50000, name);
        jobTitle = "Nurse";
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(50000);
    }

    public int getBloodDrawAmount() {
        return BLOOD_DRAW_AMOUNT;
    }

    public int getHealthCareAmount() {
        return HEALTH_CARE_AMOUNT;
    }

    public List<Patient> getPatientsUnderCare() {
        return patientsUnderCare;
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
