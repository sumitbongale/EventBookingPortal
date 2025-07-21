import java.sql.*;
import java.util.Scanner;

public class User {
    public static int loginOrRegister(Scanner sc) {
        System.out.print("1. Register\n2. Login\nChoose: ");
        int ch = sc.nextInt();
        sc.nextLine();

        try (Connection con = DatabaseConnection.getConnection()) {
            if (ch == 1) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            } else if (ch == 2) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE username=? AND password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}