import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeTest {

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
    }

    @Test
    public void doctorShouldInstantiate() {
        Employee doctor = new Doctor("Test", "Brain");
    }

    @Test
    public void patientShouldInstantiate() {
        Patient patient = new Patient();
    }

    @Test
    public void nurseShouldInstantiate() {
        Employee nurse = new Nurse("Test");
    }

    @Test
    public void receptionistShouldInstantiate() {
        Employee receptionist = new Receptionist("Test", false);
    }

    @Test
    public void janitorShouldInstantiate() {
        Employee janitor = new Janitor("Test", false);
    }

    @Test
    public void doctorShouldBeAbleToDrawBlood() {
        Doctor doctor = new Doctor("Test", "Brain");

        int previous = patient.getBloodLevel();

        doctor.drawBlood(patient);

        assertThat(patient.getBloodLevel()).isEqualTo(previous - doctor.getBloodDrawAmount());
    }

    @Test
    public void nurseShouldBeAbleToDrawBlood() {
        Nurse nurse = new Nurse("Test");

        int previous = patient.getBloodLevel();

        nurse.drawBlood(patient);

        assertThat(patient.getBloodLevel()).isEqualTo(previous - nurse.getBloodDrawAmount());
    }

    @Test
    public void doctorShouldBeAbleToCareForPatient() {
        Doctor doctor = new Doctor("Test", "Brain");

        int previous = patient.getHealthLevel();

        doctor.careForPatient(patient);

        assertThat(patient.getHealthLevel()).isEqualTo(previous + doctor.getHealthCareAmount());
    }

    @Test
    public void nurseShouldBeAbleToCareForPatient() {
        Nurse nurse = new Nurse("Test");

        int previous = patient.getHealthLevel();

        nurse.careForPatient(patient);

        assertThat(patient.getHealthLevel()).isEqualTo(previous + nurse.getHealthCareAmount());
    }

    @Test
    public void doctorShouldBePaidNinetyThousand() {
        Doctor doctor = new Doctor("Test", "Brain");

        assertAll(() -> {
                    doctor.getPaid();
                    assertTrue(doctor.isPaid());
                },
                () -> assertTrue(doctor.getSalary().equalsIgnoreCase("$90,000.00")));
    }

    @Test
    public void nurseShouldBePaidFiftyThousand() {
        Nurse nurse = new Nurse("Test");

        assertAll(() -> {
                    nurse.getPaid();
                    assertTrue(nurse.isPaid());
                },
                () -> assertTrue(nurse.getSalary().equalsIgnoreCase("$50,000.00")));
    }

    @Test
    public void receptionistShouldBePaid() {
        Receptionist receptionist = new Receptionist("Test", false);

        assertAll(() -> {
                    receptionist.getPaid();
                    assertTrue(receptionist.isPaid());
                },
                () -> assertTrue(receptionist.getSalary().equalsIgnoreCase("$45,000.00")));
    }

    @Test
    public void janitorShouldBePaid() {
        Janitor janitor = new Janitor("Test", false);

        assertAll(() -> {
                    janitor.getPaid();
                    assertTrue(janitor.isPaid());
                },
                () -> assertTrue(janitor.getSalary().equalsIgnoreCase("$40,000.00")));
    }

    @Test
    public void allEmployeesHaveProperToString() {
        EmployeeRoster roster = new EmployeeRoster();
        Doctor doctor = new Doctor("John", "Brain");
        Nurse nurse = new Nurse("Jane");
        Receptionist receptionist = new Receptionist("Jeff", false);
        Janitor janitor = new Janitor("Doug", false);

        roster.addToRoster(doctor, nurse, receptionist, janitor);

        assertThatCode(roster::printAllEmployees).doesNotThrowAnyException();
    }

    @Test
    public void janitorsAndReceptionistsAreWorkers() {
        Janitor janitor = new Janitor("Test", false);
        Receptionist receptionist = new Receptionist("Test", false);

        assertAll(() -> assertTrue(janitor instanceof Worker));
    }
}
