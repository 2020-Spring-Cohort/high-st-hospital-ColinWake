import java.text.NumberFormat;
import java.util.Locale;

public abstract class Employee {

    protected int salary;

    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    private int employeeNumber;

    private String name;

    private boolean paid;

    protected String jobTitle;

    private static int employeeCount = 0;

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Employee(int salary, String name) {
        this.salary = salary;
        employeeCount++;
        this.employeeNumber = employeeCount;
        this.name = name;
    }

    public NumberFormat getCurrencyFormat() {
        return currencyFormat;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void pay() {
        paySalary();
        System.out.println(getJobTitle() + " " + getName() + " was paid their salary of " + getSalary());
        paid = true;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public String toString() {
        return getJobTitle() + " " + getName() +
                " | Salary: " + getSalary() +
                " | Employee Number: " + getEmployeeNumber() +
                " | Paid: " + (isPaid() ? "yes" : "no");
    }

    public abstract String getSalary();

    private void paySalary() throws RuntimeException {

        if (isPaid()) {
            throw new RuntimeException(getName() + " was already paid!");
        }

        paid = true;
    }
}
