import java.sql.*;
import java.util.Scanner;

public class RequestManagement {

    private Connection connection;

    public RequestManagement() {
        connection = DBConnection.getConnection();
    }

    public void registerRequest() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Requester Name: ");
            String requesterName = scanner.nextLine();
            System.out.println("Enter Blood Group Requested: ");
            String bloodGroupRequested = scanner.nextLine();
            System.out.println("Enter Request Date (YYYY-MM-DD): ");
            String requestDate = scanner.nextLine();

            String query = "INSERT INTO Request (requester_name, blood_group_requested, request_date, request_status) VALUES (?, ?, ?, 'Pending')";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, requesterName);
                stmt.setString(2, bloodGroupRequested);
                stmt.setDate(3, Date.valueOf(requestDate));
                stmt.executeUpdate();
                System.out.println("Request registered successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewRequestDetails() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Request ID to view details: ");
            int requestId = scanner.nextInt();

            String query = "SELECT * FROM Request WHERE request_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, requestId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Request ID: " + rs.getInt("request_id"));
                    System.out.println("Requester Name: " + rs.getString("requester_name"));
                    System.out.println("Blood Group Requested: " + rs.getString("blood_group_requested"));
                    System.out.println("Request Date: " + rs.getDate("request_date"));
                    System.out.println("Request Status: " + rs.getString("request_status"));
                } else {
                    System.out.println("Request not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRequestStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Request ID to update: ");
            int requestId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter new Request Status (Pending, Fulfilled, Cancelled): ");
            String requestStatus = scanner.nextLine();

            String query = "UPDATE Request SET request_status = ? WHERE request_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, requestStatus);
                stmt.setInt(2, requestId);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Request status updated successfully.");
                } else {
                    System.out.println("Request not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRequest() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Request ID to delete: ");
            int requestId = scanner.nextInt();

            String query = "DELETE FROM Request WHERE request_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, requestId);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Request deleted successfully.");
                } else {
                    System.out.println("Request not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
