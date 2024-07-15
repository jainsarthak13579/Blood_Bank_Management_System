import java.util.Scanner;

public class Main {

    private static DonorManagement donorManagement = new DonorManagement();
    private static InventoryManagement inventoryManagement = new InventoryManagement();
    private static RequestManagement requestManagement = new RequestManagement();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = readIntegerInput();

            switch (choice) {
                case 1:
                    manageDonors();
                    break;
                case 2:
                    manageInventory();
                    break;
                case 3:
                    manageRequests();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("Blood Bank Management System");
        System.out.println("1. Donor Management");
        System.out.println("2. Inventory Management");
        System.out.println("3. Request Management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int readIntegerInput() {
        int choice = 0;
        boolean isValidInput = false;
        
        while (!isValidInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return choice;
    }

    private static void manageDonors() {
        printDonorManagementMenu();
        int choice = readIntegerInput();

        switch (choice) {
            case 1:
                donorManagement.addDonor();
                break;
            case 2:
                donorManagement.viewDonorDetails();
                break;
            case 3:
                donorManagement.updateDonorInfo();
                break;
            case 4:
                donorManagement.deleteDonor();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printDonorManagementMenu() {
        System.out.println("Donor Management");
        System.out.println("1. Add Donor");
        System.out.println("2. View Donor Details");
        System.out.println("3. Update Donor Information");
        System.out.println("4. Delete Donor");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void manageInventory() {
        printInventoryManagementMenu();
        int choice = readIntegerInput();

        switch (choice) {
            case 1:
                inventoryManagement.addDonation();
                break;
            case 2:
                inventoryManagement.viewInventoryDetails();
                break;
            case 3:
                inventoryManagement.updateInventoryInfo();
                break;
            case 4:
                inventoryManagement.deleteInventory();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printInventoryManagementMenu() {
        System.out.println("Inventory Management");
        System.out.println("1. Add Donation");
        System.out.println("2. View Inventory Details");
        System.out.println("3. Update Inventory Information");
        System.out.println("4. Delete Inventory");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void manageRequests() {
        printRequestManagementMenu();
        int choice = readIntegerInput();

        switch (choice) {
            case 1:
                requestManagement.registerRequest();
                break;
            case 2:
                requestManagement.viewRequestDetails();
                break;
            case 3:
                requestManagement.updateRequestStatus();
                break;
            case 4:
                requestManagement.deleteRequest();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printRequestManagementMenu() {
        System.out.println("Request Management");
        System.out.println("1. Register Request");
        System.out.println("2. View Request Details");
        System.out.println("3. Update Request Status");
        System.out.println("4. Delete Request");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }
}
