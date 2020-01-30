import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Occupants {

    private final Map<String, Employee> allEmployees = new HashMap<>();

    private final Map<String, Patient> patients = new HashMap<>();

//    private final Multimap<HealthCareProfessional, Patient> patientsUnderCare = MultimapBuilder.hashKeys().arrayListValues().build();

    public Map<String, Employee> getAllEmployees() {
        return allEmployees;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    private final Predicate<Employee> onlyAbleToHelp = e -> e instanceof HealthCareProfessional;

//    private final Predicate<Employee> ableToStartWork = e -> {
//        if (e instanceof Worker)
//            return !((Worker) e).isWorking();
//        return false;
//    };

//    public Multimap<HealthCareProfessional, Patient> getPatientsUnderCare() {
//        return patientsUnderCare;
//    }

    public Predicate<Employee> getOnlyAbleToHelp() {
        return onlyAbleToHelp;
    }

//    public Predicate<Employee> getAbleToStartWork() {
//        return ableToStartWork;
//    }

    public void addToOccupants(Employee employee) {
        getAllEmployees().put(employee.getName(), employee);
    }

    public void addToOccupants(Patient patient) {
        getPatients().put(patient.getName().toUpperCase(), patient);
    }

    public void addToOccupants(Employee... employees) {
        for (Employee employee : employees) {
            getAllEmployees().put(employee.getName().toUpperCase(), employee);
        }
    }

    public void fireEmployee(Employee employee) {
        getAllEmployees().remove(employee.getName().toUpperCase());
    }

    public void payAllEmployees() {
        getAllEmployees().values().forEach(Employee::pay);
    }

    public void printAllEmployees() {
        System.out.println("Staff:");
        getAllEmployees().values().forEach(System.out::println);
    }

    public void printAllPatients() {
        System.out.println("Patients:");
        getPatients().values().forEach(System.out::println);
    }

//    public void makeChosenEmployeeWork(Scanner input) {
//        getAllEmployees().stream().filter(getAbleToStartWork()).forEach(System.out::println);
//
//        System.out.println("Enter an employee's name to make them start working!");
//
//        String chosenEmployee = input.nextLine();
//
//        Optional<Employee> employeeOptional = getAllEmployees().stream().filter(e ->
//                e.getName().equalsIgnoreCase(chosenEmployee) && getAbleToStartWork().test(e)).findFirst();
//
//        if (employeeOptional.isPresent()) {
//            Worker employee = (Worker) employeeOptional.get();
//
//            employee.work();
//        } else {
//            System.out.println("No employee is named " + chosenEmployee);
//        }
//    }

    public void makeDoctorOrNurseDrawBlood(Scanner input) {
        System.out.println("Choose a patient to draw blood from");

        getPatients().values().forEach(System.out::println);

        String chosenPatient = input.nextLine();

        if (getPatients().get(chosenPatient.toUpperCase()) != null) {
            Patient chosen = getPatients().get(chosenPatient.toUpperCase());

            getAllEmployees().values().stream().filter(getOnlyAbleToHelp()).forEach(System.out::println);

            System.out.println("Choose medical personnel to draw blood from " + chosen.getName());

            String chosenPersonnel = input.nextLine();

            HealthCareProfessional toCare = (HealthCareProfessional) getAllEmployees().get(chosenPersonnel.toUpperCase());

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

            HealthCareProfessional toCare = (HealthCareProfessional) getAllEmployees().get(chosenPersonnel.toUpperCase());

            toCare.careForPatient(chosen);

            System.out.println(((Employee) toCare).getName() + " has treated " + chosen.getName());

            System.out.println(chosen);
        }
    }
}
