public class Receptionist extends Employee implements Worker {

    private boolean onPhone = false;

    public Receptionist(String name, boolean onPhone) {
        super(45000, name);
        jobTitle = "Receptionist";
        this.onPhone = onPhone;
    }

    public boolean isOnPhone() {
        return onPhone;
    }

    public void setOnPhone(boolean onPhone) {
        this.onPhone = onPhone;
    }

    @Override
    public String getSalary() {
        return getCurrencyFormat().format(45000);
    }

    @Override
    public void work() {
        onPhone ^= true;
        System.out.println(getName() + " is " + (isOnPhone() ? "" : "not") + "on the phone!");
    }

    @Override
    public String toString() {
        return super.toString() + " | On the phone: " + (isWorking() ? "yes" : "no");
    }

    @Override
    public boolean isWorking() {
        return isOnPhone();
    }
}
