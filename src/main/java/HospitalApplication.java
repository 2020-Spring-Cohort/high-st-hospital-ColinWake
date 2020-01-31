import java.util.Scanner;

public class HospitalApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Occupants occupants = new Occupants();

        System.out.println("Welcome to High St. Hospital!");

        String command;

        Doctor doctor = new Doctor("Eric", "Foot");

        Janitor janitor = new Janitor("John", false);

        Nurse nurse = new Nurse("Kylie");

        Receptionist receptionist = new Receptionist("Aaron", false);

        EmergencyDispatcher tina = new EmergencyDispatcher("Tina");

        occupants.addToOccupants(doctor, janitor, nurse, receptionist, tina);

        Patient bobby = new Patient("Bobby");

        Patient jim = new Patient("Jim");

        Patient henry = new Patient("Henry");

        occupants.addToOccupants(bobby, jim, henry);

        do {
            System.out.println("Enter a command");

            command = input.nextLine();

            switch (command.toLowerCase().trim()) {

                case "quit":
                    break;
                case "list":
                    occupants.printAllEmployees();

                    occupants.printAllPatients();

                    break;
                case "care":
                    occupants.makeDoctorOrNurseCare(input);

                    break;

                case "draw":
                    occupants.makeDoctorOrNurseDrawBlood(input);

                    break;

                case "intake":
                    System.out.println("Name the new patient");

                    String name = input.nextLine();

                    occupants.addToOccupants(new Patient(name));

                    System.out.println(name + " added as a patient");

                    break;

                case "fire":
                    occupants.printAllEmployees();

                    System.out.println("Who do you want to fire?");

                    String chosen = input.nextLine();

                    occupants.fireEmployee(chosen);

                    break;

                case "hire":
                    occupants.hireNewEmployee(input);

                    break;
                case "pay":
                    occupants.payAllEmployees();

                    break;
                case "work":
                    occupants.makeEmployeeWork(input);

                    break;
                case "search":
                    occupants.printEmployeeFromSearch(input);

                    break;
                default:
                    System.out.println("Unknown command, try again");
            }

        } while (!command.equalsIgnoreCase("quit"));


        System.out.println("Goodbye!");
    }
}
