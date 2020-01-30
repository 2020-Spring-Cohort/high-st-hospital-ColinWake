public class Janitor extends Employee implements Worker {

    private boolean sweeping;

    public Janitor(String name, boolean sweeping) {
        super(40000, name);
        jobTitle = "Janitor";
        this.sweeping = sweeping;
    }

    public boolean isSweeping() {
        return sweeping;
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(40000);
    }

    @Override
    public void work() {
        sweeping ^= true;
        System.out.println(getName() + " has " + (isSweeping() ? "started" : "stopped") + " sweeping!");
    }

    @Override
    public String toString() {
        return super.toString() + " | Sweeping: " + (isWorking() ? "yes" : "no");
    }

    @Override
    public boolean isWorking() {
        return isSweeping();
    }
}
