import java.util.Scanner;

public class HospitalApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Occupants occupants = new Occupants();

        System.out.println("Welcome to High St. Hospital!");

        String command;

        boolean employeesPaid = false;

        Doctor doctor = new Doctor("Eric", "Foot");

        Janitor janitor = new Janitor("John", false);

        Nurse nurse = new Nurse("Kylie");

        Receptionist receptionist = new Receptionist("Aaron", false);

        occupants.addToOccupants(doctor, janitor, nurse, receptionist);

        Patient patient = new Patient("Bobby");

        occupants.addToOccupants(patient);

        do {
            System.out.println("Enter a command");

            command = input.nextLine();

            switch (command.toLowerCase()) {

                case "quit":
                    break;
//                case "work":
//                    occupants.makeChosenEmployeeWork(input);
//
//                    break;

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
                case "pay":
                    if (employeesPaid) {
                        System.out.println("You already paid all employees!");

                        break;
                    }
                    occupants.payAllEmployees();

                    employeesPaid = true;

                    break;
                default:
                    System.out.println("Unknown command, try again");
            }

        } while (!command.equalsIgnoreCase("quit"));


        System.out.println("Thanks for playing!");
    }
}
