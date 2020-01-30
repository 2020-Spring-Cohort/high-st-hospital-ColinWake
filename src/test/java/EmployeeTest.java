import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeTest {

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient("Jeff");
    }

    @Test
    public void doctorShouldInstantiate() {
        Employee doctor = new Doctor("Test", "Brain");
    }

    @Test
    public void patientShouldInstantiate() {
        Patient patient = new Patient("Test");
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
                    doctor.pay();
                    System.out.println(doctor);
                    assertTrue(doctor.isPaid());
                },
                () -> assertTrue(doctor.getSalary().equalsIgnoreCase("$90,000.00")));

        assertThatThrownBy(() -> doctor.pay()).isInstanceOf(RuntimeException.class).hasMessageMatching(doctor.getName() + " was already paid!");
    }

    @Test
    public void nurseShouldBePaidFiftyThousand() {
        Nurse nurse = new Nurse("Test");

        assertAll(() -> {
                    nurse.pay();
                    System.out.println(nurse);
                    assertTrue(nurse.isPaid());
                },
                () -> assertTrue(nurse.getSalary().equalsIgnoreCase("$50,000.00")));

        assertThatThrownBy(() -> nurse.pay()).isInstanceOf(RuntimeException.class).hasMessageMatching(nurse.getName() + " was already paid!");
    }

    @Test
    public void receptionistShouldBePaid() {
        Receptionist receptionist = new Receptionist("Test", false);

        assertAll(() -> {
                    receptionist.pay();
                    System.out.println(receptionist);
                    assertTrue(receptionist.isPaid());
                },
                () -> assertTrue(receptionist.getSalary().equalsIgnoreCase("$45,000.00")));

        assertThatThrownBy(() -> receptionist.pay()).isInstanceOf(RuntimeException.class).hasMessageMatching(receptionist.getName() + " was already paid!");
    }

    @Test
    public void janitorShouldBePaid() {
        Janitor janitor = new Janitor("Test", false);

        assertAll(() -> {
                    janitor.pay();
                    System.out.println(janitor);
                    assertTrue(janitor.isPaid());
                },
                () -> assertTrue(janitor.getSalary().equalsIgnoreCase("$40,000.00")));

        assertThatThrownBy(() -> janitor.pay()).isInstanceOf(RuntimeException.class).hasMessageMatching(janitor.getName() + " was already paid!");
    }

    @Test
    public void allEmployeesHaveProperToString() {
        Occupants occupants = new Occupants();
        Doctor doctor = new Doctor("John", "Brain");
        Nurse nurse = new Nurse("Jane");
        Receptionist receptionist = new Receptionist("Jeff", false);
        Janitor janitor = new Janitor("Doug", false);

        occupants.addToOccupants(doctor, nurse, receptionist, janitor);

        assertThatCode(occupants::printAllEmployees).doesNotThrowAnyException();
    }

    @Test
    public void janitorsAndReceptionistsAreWorkers() {
        Janitor janitor = new Janitor("Test", false);
        Receptionist receptionist = new Receptionist("Test", false);

        assertAll(() -> assertTrue(janitor instanceof Worker));
    }

    @Test
    public void patientShouldHaveOverriddenToString() {
        assertThatCode(() -> {
            Patient patient = new Patient("Henry");

            System.out.println(patient);
        }).doesNotThrowAnyException();
    }
}
