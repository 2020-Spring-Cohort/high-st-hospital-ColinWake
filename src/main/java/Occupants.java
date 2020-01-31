import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Occupants {

    private final Map<String, Employee> allEmployees = new HashMap<>();

    private final Map<String, Patient> patients = new HashMap<>();

    public Map<String, Employee> getAllEmployees() {
        return allEmployees;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    private final Predicate<Employee> onlyAbleToHelp = e -> e instanceof MedicalProfessional;

    public Predicate<Employee> getOnlyAbleToHelp() {
        return onlyAbleToHelp;
    }

    public void addToOccupants(Employee employee) {
        getAllEmployees().put(employee.getName().toUpperCase(), employee);
    }

    public void addToOccupants(Patient patient) {
        getPatients().put(patient.getName().toUpperCase(), patient);
    }

    public void addToOccupants(Patient... patients) {
        for (Patient patient : patients) {
            getPatients().put(patient.getName().toUpperCase(), patient);
        }
    }

    public void addToOccupants(Employee... employees) {
        for (Employee employee : employees) {
            getAllEmployees().put(employee.getName().toUpperCase(), employee);
        }
    }

    public void fireEmployee(Employee employee) {
        if (getAllEmployees().containsValue(employee)) {
            getAllEmployees().remove(employee.getName().toUpperCase());

            System.out.println(employee.getName() + " was fired");
        } else {
            System.out.println(employee.getName() + " isn't in the building(?)");
        }
    }

    public void fireEmployee(String name) {
        if (getAllEmployees().get(name.toUpperCase()) != null) {
            getAllEmployees().remove(getAllEmployees().get(name.toUpperCase()).getName().toUpperCase());

            System.out.println(name + " was fired");
        } else {
            System.out.println(name + " doesn't work here");
        }
    }

    public void hireNewEmployee(Scanner input) {
        System.out.println("Which job are you hiring for? (Doctor, Janitor, Nurse, Receptionist, Dispatcher)");

        String chosenJob = input.nextLine();


        switch (chosenJob.toLowerCase()) {

            case "doctor":
                System.out.println("What is the doctor's name?");

                String name = input.nextLine();

                System.out.println("What is this doctor's specialty?");

                String specialty = input.nextLine();

                addToOccupants(new Doctor(name, specialty));

                announceNewHire(name, chosenJob, specialty);

                break;
            case "janitor":
                System.out.println("What is the janitor's name?");

                name = input.nextLine();

                addToOccupants(new Janitor(name, false));

                announceNewHire(name, chosenJob);

                break;
            case "nurse":
                System.out.println("What is the nurse's name?");

                name = input.nextLine();

                addToOccupants(new Nurse(name));

                announceNewHire(name, chosenJob);

                break;
            case "receptionist":
                System.out.println("What is the receptionist's name?");

                name = input.nextLine();

                addToOccupants(new Receptionist(name, false));

                announceNewHire(name, chosenJob);

                break;
            case "dispatcher":
                System.out.println("What is the emergency dispatcher's name?");

                name = input.nextLine();

                addToOccupants(new EmergencyDispatcher(name));

                announceNewHire(name, chosenJob);

                break;
            default:
                System.out.println("That job doesn't exist at this hospital");
        }
    }

    public void announceNewHire(String name, String job) {
        System.out.println(name + " has joined the staff team as a " + job);
    }

    public void announceNewHire(String name, String job, String specialty) {
        System.out.println(name + " has joined the staff team as a " + specialty + " " + job);
    }

    public void payAllEmployees() {
        getAllEmployees().values().forEach(e -> {
            if (!e.isPaid()) e.pay();
        });
    }

    public void printAllWorkers() {
        getAllEmployees().values().stream().filter(e -> e instanceof Worker).forEach(System.out::println);
    }

    public void makeEmployeeWork(Worker worker) {
        worker.work();
    }

    public void makeEmployeeWork(Scanner input) {
        printAllWorkers();

        System.out.println("Who do you want to start/stop working?");

        String name = input.nextLine();

        if (getAllEmployees().get(name.toUpperCase()) != null) {
            Employee employee = getAllEmployees().get(name.toUpperCase());

            if (employee instanceof Worker) {
                makeEmployeeWork((Worker) employee);
            } else {
                System.out.println(name + " isn't a worker");
            }
        } else {
            System.out.println(name + " doesn't work here");
        }
    }

    public void printAllEmployees() {
        System.out.println("Staff:");
        getAllEmployees().values().forEach(System.out::println);
    }

    public void printAllPatients() {
        System.out.println("Patients:");
        getPatients().values().forEach(System.out::println);
    }

    public void makeDoctorOrNurseDrawBlood(Scanner input) {
        System.out.println("Choose a patient to draw blood from");

        getPatients().values().forEach(System.out::println);

        String chosenPatient = input.nextLine();

        if (getPatients().get(chosenPatient.toUpperCase()) != null) {
            Patient chosen = getPatients().get(chosenPatient.toUpperCase());

            getAllEmployees().values().stream().filter(getOnlyAbleToHelp()).forEach(System.out::println);

            System.out.println("Choose medical personnel to draw blood from " + chosen.getName());

            String chosenPersonnel = input.nextLine();

            MedicalProfessional toCare = (MedicalProfessional) getAllEmployees().get(chosenPersonnel.toUpperCase());

            toCare.drawBlood(chosen);

            System.out.println(((Employee) toCare).getName() + " has drawn blood from " + chosen.getName());

            System.out.println(chosen);
        }
    }

    public void makeDoctorOrNurseCare(Scanner input) {
        System.out.println("Choose a patient to treat");

        getPatients().values().forEach(System.out::println);

        String chosenPatient = input.nextLine();

        if (getPatients().get(chosenPatient.toUpperCase()) != null) {
            Patient chosen = getPatients().get(chosenPatient.toUpperCase());

            getAllEmployees().values().stream().filter(getOnlyAbleToHelp()).forEach(System.out::println);

            System.out.println("Choose medical personnel to treat " + chosen.getName());

            String chosenPersonnel = input.nextLine();

            MedicalProfessional toCare = (MedicalProfessional) getAllEmployees().get(chosenPersonnel.toUpperCase());

            toCare.careForPatient(chosen);

            System.out.println(((Employee) toCare).getName() + " has treated " + chosen.getName());

            System.out.println(chosen);
        }
    }

    public void printEmployeeFromSearch(Scanner input) {
        System.out.println("Enter the name of the employee you're trying to find");

        String name = input.nextLine();

        if (getAllEmployees().get(name.toUpperCase()) != null) {
            System.out.println(getAllEmployees().get(name.toUpperCase()));
        } else {
            System.out.println("Nobody named " + name + " works here");
        }
    }
}
