import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;

public class DonorManagement {

    private Connection connection;

    public DonorManagement() {
        connection = DBConnection.getConnection(); // Initialize the connection properly
    }

    public void addDonor() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donor Name: ");
            String donorName = scanner.nextLine();
            System.out.println("Enter Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.println("Enter Contact Number: ");
            String contactNumber = scanner.nextLine();
            System.out.println("Enter Email: ");
            String email = scanner.nextLine();
            System.out.println("Enter Last Donation Date (YYYY-MM-DD): ");
            String lastDonationDate = scanner.nextLine();

            String query = "INSERT INTO Donor (donor_name, blood_group, contact_number, email, last_donation_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, donorName);
                stmt.setString(2, bloodGroup);
                stmt.setString(3, contactNumber);
                stmt.setString(4, email);
                stmt.setDate(5, Date.valueOf(lastDonationDate));
                stmt.executeUpdate();
                System.out.println("Donor added successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDonorDetails() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donor ID to view details: ");
            int donorId = scanner.nextInt();

            String query = "SELECT * FROM Donor WHERE donor_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donorId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Donor ID: " + rs.getInt("donor_id"));
                    System.out.println("Donor Name: " + rs.getString("donor_name"));
                    System.out.println("Blood Group: " + rs.getString("blood_group"));
                    System.out.println("Contact Number: " + rs.getString("contact_number"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Last Donation Date: " + rs.getDate("last_donation_date"));
                } else {
                    System.out.println("Donor not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDonorInfo() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donor ID to update: ");
            int donorId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter new Donor Name: ");
            String donorName = scanner.nextLine();
            System.out.println("Enter new Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.println("Enter new Contact Number: ");
            String contactNumber = scanner.nextLine();
            System.out.println("Enter new Email: ");
            String email = scanner.nextLine();
            System.out.println("Enter new Last Donation Date (YYYY-MM-DD): ");
            String lastDonationDate = scanner.nextLine();

            String query = "UPDATE Donor SET donor_name = ?, blood_group = ?, contact_number = ?, email = ?, last_donation_date = ? WHERE donor_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, donorName);
                stmt.setString(2, bloodGroup);
                stmt.setString(3, contactNumber);
                stmt.setString(4, email);
                stmt.setDate(5, Date.valueOf(lastDonationDate));
                stmt.setInt(6, donorId);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Donor information updated successfully.");
                } else {
                    System.out.println("Donor not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDonor() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Donor ID to delete: ");
            int donorId = scanner.nextInt();

            String query = "DELETE FROM Donor WHERE donor_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, donorId);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Donor deleted successfully.");
                } else {
                    System.out.println("Donor not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
