import java.util.Scanner;

public class HospitalApplication {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        EmployeeRoster roster = new EmployeeRoster();

        System.out.println("Welcome to High St. Hospital!");

        String command;

        do {
            System.out.println("Enter a command");

            command = input.nextLine();

            switch (command.toLowerCase()) {

                case "quit":
                    break;

                case "work":
                    roster.makeChosenEmployeeWork(input);

                    break;

                case "care":
                    roster.makeDoctorOrNurseCare(input);

                    break;
                default:
                    System.out.println("Unknown command, try again");
            }

        } while (!command.equalsIgnoreCase("quit"));


        System.out.println("Thanks for playing!");
    }
}
