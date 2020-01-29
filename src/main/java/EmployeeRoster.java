import java.util.*;
import java.util.function.Predicate;

public class EmployeeRoster {

    private final List<Employee> allEmployees = new ArrayList<>();

    private final List<Patient> patientsUnderCare = new ArrayList<>();

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    private final Predicate<Employee> onlyAbleToHelp = e -> e instanceof HealthCareProfessional;

    private final Predicate<Employee> ableToStartWork = e -> {
        if (e instanceof Worker)
            return !((Worker) e).isWorking();
        return false;
    };

    public Predicate<Employee> getOnlyAbleToHelp() {
        return onlyAbleToHelp;
    }

    public Predicate<Employee> getAbleToStartWork() {
        return ableToStartWork;
    }

    public void addToRoster(Employee employee) {
        allEmployees.add(employee);
    }

    public void addToRoster(Employee... employees) {
        allEmployees.addAll(Arrays.asList(employees));
    }

    public void printAllEmployees() {
        allEmployees.forEach(System.out::println);
    }

    public void makeChosenEmployeeWork(Scanner input) {
        getAllEmployees().stream().filter(getAbleToStartWork()).forEach(System.out::println);

        System.out.println("Enter an employee's name to make them start working!");

        String chosenEmployee = input.nextLine();

        Optional<Employee> employeeOptional = getAllEmployees().stream().filter(e ->
                e.getName().equalsIgnoreCase(chosenEmployee) && getAbleToStartWork().test(e)).findFirst();

        if (employeeOptional.isPresent()) {
            Worker employee = (Worker) employeeOptional.get();

            employee.work();
        } else {
            System.out.println("No employee is named " + chosenEmployee);
        }
    }

    public void makeDoctorOrNurseCare(Scanner input) {
        getAllEmployees().stream().filter(getOnlyAbleToHelp()).forEach(System.out::println);

        System.out.println("Enter an employee's name to make them start working!");

        String chosenEmployee = input.nextLine();

        Optional<Employee> employeeOptional = getAllEmployees().stream().filter(e ->
                e.getName().equalsIgnoreCase(chosenEmployee) && getOnlyAbleToHelp().test(e)).findFirst();

        if (employeeOptional.isPresent()) {
            HealthCareProfessional employee = (HealthCareProfessional) employeeOptional.get();


        } else {
            System.out.println("No employee is named " + chosenEmployee);
        }
    }
}
