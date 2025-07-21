import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userId = User.loginOrRegister(sc);
        if (userId == -1) {
            System.out.println("Login/Register failed.");
            return;
        }

        while (true) {
            System.out.println("\n1. Add Event\n2. Show Events\n3. Book Event\n4. View Bookings\n5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> Event.addEvent(sc);
                case 2 -> Event.showEvents();
                case 3 -> Booking.bookEvent(sc, userId);
                case 4 -> Booking.viewBookings(userId);
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}