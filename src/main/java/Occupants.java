import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Occupants {

    private final List<Employee> allEmployees = new ArrayList<>();

//    private final List<Patient>

//    private final Multimap<HealthCareProfessional, Patient> patientsUnderCare = MultimapBuilder.hashKeys().arrayListValues().build();

    public List<Employee> getAllEmployees() {
        return allEmployees;
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
        getAllEmployees().add(employee);
    }

    public void addToOccupants(Employee... employees) {
        allEmployees.addAll(Arrays.asList(employees));
    }

    public void fireEmployee(Employee employee) {
        getAllEmployees().remove(employee);
    }

    public void payAllEmployees() {
        allEmployees.forEach(Employee::pay);
    }

    public void printAllEmployees() {
        allEmployees.forEach(System.out::println);
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

    public void makeDoctorOrNurseCare(Scanner input) {
        getAllEmployees().stream().filter(getOnlyAbleToHelp()).forEach(e -> System.out.println(e.getName()));

        System.out.println("Enter an employee's name to have them treat a patient!");

        String chosenEmployee = input.nextLine();

        if (getAllEmployees().stream().anyMatch(e -> e.getName().equalsIgnoreCase(chosenEmployee))) {


        } else {
            System.out.println("No employee named " + chosenEmployee + " works here!");
        }
    }
}
