import java.sql.*;
import java.util.Scanner;

public class Event {
    public static void addEvent(Scanner sc) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("Event Name: ");
            String name = sc.nextLine();
            System.out.print("Location: ");
            String location = sc.nextLine();
            System.out.print("Date (yyyy-mm-dd): ");
            String date = sc.nextLine();
            PreparedStatement ps = con.prepareStatement("INSERT INTO events (name, location, date) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setDate(3, Date.valueOf(date));
            ps.executeUpdate();
            System.out.println("Event added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showEvents() {
        try (Connection con = DatabaseConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM events");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("location") + " | " + rs.getDate("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}