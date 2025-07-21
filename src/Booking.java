import java.sql.*;
import java.util.Scanner;

public class Booking {
    public static void bookEvent(Scanner sc, int userId) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("Enter Event ID to book: ");
            int eventId = sc.nextInt();
            sc.nextLine();
            PreparedStatement ps = con.prepareStatement("INSERT INTO bookings (user_id, event_id) VALUES (?, ?)");
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            ps.executeUpdate();
            System.out.println("Event booked.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewBookings(int userId) {
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT b.id, e.name, e.location, e.date FROM bookings b JOIN events e ON b.event_id = e.id WHERE b.user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("id") + " | Event: " + rs.getString("name") + " | Location: " + rs.getString("location") + " | Date: " + rs.getDate("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}