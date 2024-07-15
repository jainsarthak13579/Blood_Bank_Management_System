import java.sql.*;
import java.util.Scanner;

public class InventoryManagement {

    private Connection connection;

    public InventoryManagement() {
        connection = DBConnection.getConnection();
    }

    public void addDonation() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donor ID: ");
            int donorId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter Donation Date (YYYY-MM-DD): ");
            String donationDate = scanner.nextLine();
            System.out.println("Enter Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.println("Enter Quantity (in ml): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter Expiry Date (YYYY-MM-DD): ");
            String expiryDate = scanner.nextLine();

            String query = "INSERT INTO Inventory (donor_id, donation_date, blood_group, quantity, expiry_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donorId);
                stmt.setDate(2, Date.valueOf(donationDate));
                stmt.setString(3, bloodGroup);
                stmt.setInt(4, quantity);
                stmt.setDate(5, Date.valueOf(expiryDate));
                stmt.executeUpdate();
                System.out.println("Donation added to inventory successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewInventoryDetails() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donation ID to view details: ");
            int donationId = scanner.nextInt();

            String query = "SELECT * FROM Inventory WHERE donation_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donationId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Donation ID: " + rs.getInt("donation_id"));
                    System.out.println("Donor ID: " + rs.getInt("donor_id"));
                    System.out.println("Donation Date: " + rs.getDate("donation_date"));
                    System.out.println("Blood Group: " + rs.getString("blood_group"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Expiry Date: " + rs.getDate("expiry_date"));
                } else {
                    System.out.println("Donation not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInventoryInfo() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donation ID to update: ");
            int donationId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter new Donor ID: ");
            int donorId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter new Donation Date (YYYY-MM-DD): ");
            String donationDate = scanner.nextLine();
            System.out.println("Enter new Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.println("Enter new Quantity (in ml): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter new Expiry Date (YYYY-MM-DD): ");
            String expiryDate = scanner.nextLine();

            String query = "UPDATE Inventory SET donor_id = ?, donation_date = ?, blood_group = ?, quantity = ?, expiry_date = ? WHERE donation_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donorId);
                stmt.setDate(2, Date.valueOf(donationDate));
                stmt.setString(3, bloodGroup);
                stmt.setInt(4, quantity);
                stmt.setDate(5, Date.valueOf(expiryDate));
                stmt.setInt(6, donationId);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Inventory information updated successfully.");
                } else {
                    System.out.println("Donation not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInventory() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donation ID to delete: ");
            int donationId = scanner.nextInt();

            String query = "DELETE FROM Inventory WHERE donation_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donationId);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Donation deleted from inventory successfully.");
                } else {
                    System.out.println("Donation not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
