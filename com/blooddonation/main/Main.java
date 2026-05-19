//package com.blooddonation.main;
//
//import java.io.PrintStream;
//import java.util.List;
//import java.util.Scanner;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import com.blooddonation.entities.BloodBank;
//import com.blooddonation.entities.BloodInventory;
//import com.blooddonation.entities.BloodRequest;
//import com.blooddonation.entities.Donation;
//import com.blooddonation.entities.Donor;
//import com.blooddonation.entities.HealthCheck;
//import com.blooddonation.entities.Recipient;
//
//import com.blooddonation.exceptions.BloodBankNotFoundException;
//import com.blooddonation.exceptions.BloodInventoryNotFoundException;
//import com.blooddonation.exceptions.BloodRequestNotFoundException;
//import com.blooddonation.exceptions.DonationNotFoundException;
//import com.blooddonation.exceptions.DonorNotFoundException;
//import com.blooddonation.exceptions.HealthCheckNotFoundException;
//import com.blooddonation.exceptions.InvalidBloodGroupException;
//import com.blooddonation.exceptions.InvalidInputException;
//import com.blooddonation.exceptions.RecipientNotFoundException;
//
//import com.blooddonation.serviceImpl.BloodBankServiceImpl;
//import com.blooddonation.serviceImpl.BloodInventoryServiceImpl;
//import com.blooddonation.serviceImpl.BloodRequestServiceImpl;
//import com.blooddonation.serviceImpl.DonationServiceImpl;
//import com.blooddonation.serviceImpl.DonorServiceImpl;
//import com.blooddonation.serviceImpl.HealthCheckServiceImpl;
//import com.blooddonation.serviceImpl.RecipientServiceImpl;
//
//public class Main {
//
//    // Valid blood groups for validation
//    private static final String[] VALID_BLOOD_GROUPS =
//            {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
//
//    // Blood group validator
//    private static void validateBloodGroup(String bg)
//            throws InvalidBloodGroupException {
//        for (String valid : VALID_BLOOD_GROUPS) {
//            if (valid.equalsIgnoreCase(bg)) return;
//        }
//        throw new InvalidBloodGroupException(bg);
//    }
//
//    // ID validator
//    private static int parseId(String input, String field)
//            throws InvalidInputException {
//        try {
//            int id = Integer.parseInt(input.trim());
//            if (id <= 0) throw new InvalidInputException(
//                    field + " must be a positive number");
//            return id;
//        } catch (NumberFormatException e) {
//            throw new InvalidInputException(
//                    field + " must be a valid number");
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        // --- Fix Eclipse console buffering ----------------------------
//        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
//        System.setOut(ps);
//        System.setErr(ps);  // merges Hibernate logs into same stream as menu
//
//        // --- 1. Initialize Hibernate ----------------------------------
//        SessionFactory sf = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        // --- 2. Service implementations -------------------------------
//        DonorServiceImpl          donorService        = new DonorServiceImpl();
//        RecipientServiceImpl      recipientService    = new RecipientServiceImpl();
//        BloodBankServiceImpl      bloodBankService    = new BloodBankServiceImpl();
//        BloodRequestServiceImpl   bloodRequestService = new BloodRequestServiceImpl();
//        DonationServiceImpl       donationService     = new DonationServiceImpl();
//        BloodInventoryServiceImpl inventoryService    = new BloodInventoryServiceImpl();
//        HealthCheckServiceImpl    healthCheckService  = new HealthCheckServiceImpl();
//
//        // --- 3. Single Scanner for entire app -------------------------
//        Scanner sc = new Scanner(System.in);
//        int choice = 0;
//
//        // --- 4. Main menu loop ----------------------------------------
//        do {
//            System.out.println();
//            System.out.println("==========================================");
//            System.out.println("    ONLINE BLOOD DONATION SYSTEM         ");
//            System.out.println("==========================================");
//            System.out.println("  DONOR");
//            System.out.println("   1.  Register Donor");
//            System.out.println("   2.  View Donor by ID");
//            System.out.println("   3.  View All Donors");
//            System.out.println("   4.  Update Donor");
//            System.out.println("   5.  Delete Donor");
//            System.out.println("------------------------------------------");
//            System.out.println("  RECIPIENT");
//            System.out.println("   6.  Register Recipient");
//            System.out.println("   7.  View Recipient by ID");
//            System.out.println("   8.  View All Recipients");
//            System.out.println("   9.  Update Recipient");
//            System.out.println("  10.  Delete Recipient");
//            System.out.println("------------------------------------------");
//            System.out.println("  BLOOD BANK");
//            System.out.println("  11.  Add Blood Bank");
//            System.out.println("  12.  View Blood Bank by ID");
//            System.out.println("  13.  View All Blood Banks");
//            System.out.println("  14.  Update Blood Bank");
//            System.out.println("  15.  Delete Blood Bank");
//            System.out.println("------------------------------------------");
//            System.out.println("  BLOOD REQUEST");
//            System.out.println("  16.  Create Blood Request");
//            System.out.println("  17.  View Blood Request by ID");
//            System.out.println("  18.  View All Blood Requests");
//            System.out.println("  19.  Update Blood Request Status");
//            System.out.println("  20.  Delete Blood Request");
//            System.out.println("------------------------------------------");
//            System.out.println("  DONATION");
//            System.out.println("  21.  Record Donation");
//            System.out.println("  22.  View Donation by ID");
//            System.out.println("  23.  View All Donations");
//            System.out.println("  24.  Update Donation");
//            System.out.println("  25.  Delete Donation");
//            System.out.println("------------------------------------------");
//            System.out.println("  BLOOD INVENTORY");
//            System.out.println("  26.  Add Blood Inventory");
//            System.out.println("  27.  View Inventory by ID");
//            System.out.println("  28.  View All Inventories");
//            System.out.println("  29.  Update Inventory Quantity");
//            System.out.println("  30.  Delete Inventory Record");
//            System.out.println("------------------------------------------");
//            System.out.println("  HEALTH CHECK");
//            System.out.println("  31.  Add Health Check");
//            System.out.println("  32.  View Health Check by ID");
//            System.out.println("  33.  View All Health Checks");
//            System.out.println("  34.  Update Health Check");
//            System.out.println("  35.  Delete Health Check");
//            System.out.println("------------------------------------------");
//            System.out.println("   0.  Exit");
//            System.out.println("==========================================");
//            System.out.print("Enter choice: ");
//            System.out.flush();
//
//            try {
//                String input = sc.nextLine().trim();
//
//                if (input.isEmpty())
//                    throw new InvalidInputException("Choice cannot be empty");
//
//                choice = Integer.parseInt(input);
//
//                switch (choice) {
//
//                    // -- Donor -----------------------------------------
//                    case 1: {
//                        donorService.saveDonor(sf);
//                        break;
//                    }
//                    case 2: {
//                        System.out.print("Enter Donor ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donor ID");
//                        Donor donor = donorService.getDonor(sf, id);
//                        if (donor == null) throw new DonorNotFoundException(id);
//                        System.out.println(donor);
//                        break;
//                    }
//                    case 3: {
//                        List<Donor> donors = donorService.getAllDonors(sf);
//                        if (donors.isEmpty())
//                            System.out.println("No donors registered yet.");
//                        else
//                            donors.forEach(System.out::println);
//                        break;
//                    }
//                    case 4: {
//                        System.out.print("Enter Donor ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donor ID");
//                        Donor donor = donorService.getDonor(sf, id);
//                        if (donor == null) throw new DonorNotFoundException(id);
//                        donorService.updateDonor(sf, id);
//                        break;
//                    }
//                    case 5: {
//                        System.out.print("Enter Donor ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donor ID");
//                        Donor donor = donorService.getDonor(sf, id);
//                        if (donor == null) throw new DonorNotFoundException(id);
//                        donorService.deleteDonor(sf, id);
//                        break;
//                    }
//
//                    // -- Recipient -------------------------------------
//                    case 6: {
//                        recipientService.saveRecipient(sf);
//                        break;
//                    }
//                    case 7: {
//                        System.out.print("Enter Recipient ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Recipient ID");
//                        Recipient recipient = recipientService.getRecipient(sf, id);
//                        if (recipient == null) throw new RecipientNotFoundException(id);
//                        System.out.println(recipient);
//                        break;
//                    }
//                    case 8: {
//                        List<Recipient> recipients = recipientService.getAllRecipients(sf);
//                        if (recipients.isEmpty())
//                            System.out.println("No recipients registered yet.");
//                        else
//                            recipients.forEach(System.out::println);
//                        break;
//                    }
//                    case 9: {
//                        System.out.print("Enter Recipient ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Recipient ID");
//                        Recipient recipient = recipientService.getRecipient(sf, id);
//                        if (recipient == null) throw new RecipientNotFoundException(id);
//                        recipientService.updateRecipient(sf, id);
//                        break;
//                    }
//                    case 10: {
//                        System.out.print("Enter Recipient ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Recipient ID");
//                        Recipient recipient = recipientService.getRecipient(sf, id);
//                        if (recipient == null) throw new RecipientNotFoundException(id);
//                        recipientService.deleteRecipient(sf, id);
//                        break;
//                    }
//
//                    // -- Blood Bank ------------------------------------
//                    case 11: {
//                        bloodBankService.saveBloodBank(sf);
//                        break;
//                    }
//                    case 12: {
//                        System.out.print("Enter Blood Bank ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Bank ID");
//                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
//                        if (bank == null) throw new BloodBankNotFoundException(id);
//                        System.out.println(bank);
//                        break;
//                    }
//                    case 13: {
//                        List<BloodBank> banks = bloodBankService.getAllBloodBanks(sf);
//                        if (banks.isEmpty())
//                            System.out.println("No blood banks added yet.");
//                        else
//                            banks.forEach(System.out::println);
//                        break;
//                    }
//                    case 14: {
//                        System.out.print("Enter Blood Bank ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Bank ID");
//                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
//                        if (bank == null) throw new BloodBankNotFoundException(id);
//                        bloodBankService.updateBloodBank(sf, id);
//                        break;
//                    }
//                    case 15: {
//                        System.out.print("Enter Blood Bank ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Bank ID");
//                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
//                        if (bank == null) throw new BloodBankNotFoundException(id);
//                        bloodBankService.deleteBloodBank(sf, id);
//                        break;
//                    }
//
//                    // -- Blood Request ---------------------------------
//                    case 16: {
//                        bloodRequestService.saveBloodRequest(sf);
//                        break;
//                    }
//                    case 17: {
//                        System.out.print("Enter Blood Request ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Request ID");
//                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
//                        if (request == null) throw new BloodRequestNotFoundException(id);
//                        System.out.println(request);
//                        break;
//                    }
//                    case 18: {
//                        List<BloodRequest> requests = bloodRequestService.getAllBloodRequests(sf);
//                        if (requests.isEmpty())
//                            System.out.println("No blood requests found.");
//                        else
//                            requests.forEach(System.out::println);
//                        break;
//                    }
//                    case 19: {
//                        System.out.print("Enter Blood Request ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Request ID");
//                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
//                        if (request == null) throw new BloodRequestNotFoundException(id);
//                        bloodRequestService.updateBloodRequest(sf, id);
//                        break;
//                    }
//                    case 20: {
//                        System.out.print("Enter Blood Request ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Blood Request ID");
//                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
//                        if (request == null) throw new BloodRequestNotFoundException(id);
//                        bloodRequestService.deleteBloodRequest(sf, id);
//                        break;
//                    }
//
//                    // -- Donation --------------------------------------
//                    case 21: {
//                        donationService.saveDonation(sf);
//                        break;
//                    }
//                    case 22: {
//                        System.out.print("Enter Donation ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donation ID");
//                        Donation donation = donationService.getDonation(sf, id);
//                        if (donation == null) throw new DonationNotFoundException(id);
//                        System.out.println(donation);
//                        break;
//                    }
//                    case 23: {
//                        List<Donation> donations = donationService.getAllDonations(sf);
//                        if (donations.isEmpty())
//                            System.out.println("No donations recorded yet.");
//                        else
//                            donations.forEach(System.out::println);
//                        break;
//                    }
//                    case 24: {
//                        System.out.print("Enter Donation ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donation ID");
//                        Donation donation = donationService.getDonation(sf, id);
//                        if (donation == null) throw new DonationNotFoundException(id);
//                        donationService.updateDonation(sf, id);
//                        break;
//                    }
//                    case 25: {
//                        System.out.print("Enter Donation ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Donation ID");
//                        Donation donation = donationService.getDonation(sf, id);
//                        if (donation == null) throw new DonationNotFoundException(id);
//                        donationService.deleteDonation(sf, id);
//                        break;
//                    }
//
//                    // -- Blood Inventory -------------------------------
//                    case 26: {
//                        inventoryService.saveBloodInventory(sf);
//                        break;
//                    }
//                    case 27: {
//                        System.out.print("Enter Inventory ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Inventory ID");
//                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
//                        if (inv == null) throw new BloodInventoryNotFoundException(id);
//                        System.out.println(inv);
//                        break;
//                    }
//                    case 28: {
//                        List<BloodInventory> inventories = inventoryService.getAllBloodInventories(sf);
//                        if (inventories.isEmpty())
//                            System.out.println("No inventory records found.");
//                        else
//                            inventories.forEach(System.out::println);
//                        break;
//                    }
//                    case 29: {
//                        System.out.print("Enter Inventory ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Inventory ID");
//                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
//                        if (inv == null) throw new BloodInventoryNotFoundException(id);
//                        inventoryService.updateBloodInventory(sf, id);
//                        break;
//                    }
//                    case 30: {
//                        System.out.print("Enter Inventory ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Inventory ID");
//                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
//                        if (inv == null) throw new BloodInventoryNotFoundException(id);
//                        inventoryService.deleteBloodInventory(sf, id);
//                        break;
//                    }
//
//                    // -- Health Check ----------------------------------
//                    case 31: {
//                        healthCheckService.saveHealthCheck(sf);
//                        break;
//                    }
//                    case 32: {
//                        System.out.print("Enter Health Check ID: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Health Check ID");
//                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
//                        if (hc == null) throw new HealthCheckNotFoundException(id);
//                        System.out.println(hc);
//                        break;
//                    }
//                    case 33: {
//                        List<HealthCheck> checks = healthCheckService.getAllHealthChecks(sf);
//                        if (checks.isEmpty())
//                            System.out.println("No health check records found.");
//                        else
//                            checks.forEach(System.out::println);
//                        break;
//                    }
//                    case 34: {
//                        System.out.print("Enter Health Check ID to Update: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Health Check ID");
//                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
//                        if (hc == null) throw new HealthCheckNotFoundException(id);
//                        healthCheckService.updateHealthCheck(sf, id);
//                        break;
//                    }
//                    case 35: {
//                        System.out.print("Enter Health Check ID to Delete: ");
//                        System.out.flush();
//                        int id = parseId(sc.nextLine(), "Health Check ID");
//                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
//                        if (hc == null) throw new HealthCheckNotFoundException(id);
//                        healthCheckService.deleteHealthCheck(sf, id);
//                        break;
//                    }
//
//                    // -- Exit ------------------------------------------
//                    case 0:
//                        System.out.println("\nThank you for using the Online Blood Donation System.");
//                        System.out.println("Every drop counts. You save a life.");
//                        System.out.flush();
//                        break;
//
//                    default:
//                        System.out.println("Invalid choice. Please enter a number between 0 and 35.");
//                }
//
//            } catch (DonorNotFoundException e) {
//                System.out.println("ERROR - Donor: " + e.getMessage());
//
//            } catch (RecipientNotFoundException e) {
//                System.out.println("ERROR - Recipient: " + e.getMessage());
//
//            } catch (BloodBankNotFoundException e) {
//                System.out.println("ERROR - Blood Bank: " + e.getMessage());
//
//            } catch (BloodRequestNotFoundException e) {
//                System.out.println("ERROR - Blood Request: " + e.getMessage());
//
//            } catch (DonationNotFoundException e) {
//                System.out.println("ERROR - Donation: " + e.getMessage());
//
//            } catch (BloodInventoryNotFoundException e) {
//                System.out.println("ERROR - Blood Inventory: " + e.getMessage());
//
//            } catch (HealthCheckNotFoundException e) {
//                System.out.println("ERROR - Health Check: " + e.getMessage());
//
//            } catch (InvalidInputException e) {
//                System.out.println("ERROR - Input: " + e.getMessage());
//
//            } catch (NumberFormatException e) {
//                System.out.println("ERROR: Please enter a valid number for the menu choice.");
//
//            } catch (Exception e) {
//                System.out.println("ERROR: " + e.getMessage());
//            }
//
//        } while (choice != 0);
//
//        // --- 5. Cleanup -----------------------------------------------
//        sf.close();
//        sc.close();
//    }
//}

package com.blooddonation.main;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blooddonation.entities.BloodBank;
import com.blooddonation.entities.BloodInventory;
import com.blooddonation.entities.BloodRequest;
import com.blooddonation.entities.Donation;
import com.blooddonation.entities.Donor;
import com.blooddonation.entities.HealthCheck;
import com.blooddonation.entities.Recipient;

import com.blooddonation.exceptions.BloodBankNotFoundException;
import com.blooddonation.exceptions.BloodInventoryNotFoundException;
import com.blooddonation.exceptions.BloodRequestNotFoundException;
import com.blooddonation.exceptions.DonationNotFoundException;
import com.blooddonation.exceptions.DonorNotFoundException;
import com.blooddonation.exceptions.HealthCheckNotFoundException;
import com.blooddonation.exceptions.InvalidBloodGroupException;
import com.blooddonation.exceptions.InvalidInputException;
import com.blooddonation.exceptions.RecipientNotFoundException;

import com.blooddonation.serviceImpl.BloodBankServiceImpl;
import com.blooddonation.serviceImpl.BloodInventoryServiceImpl;
import com.blooddonation.serviceImpl.BloodRequestServiceImpl;
import com.blooddonation.serviceImpl.DonationServiceImpl;
import com.blooddonation.serviceImpl.DonorServiceImpl;
import com.blooddonation.serviceImpl.HealthCheckServiceImpl;
import com.blooddonation.serviceImpl.RecipientServiceImpl;

public class Main {

    // Valid blood groups for validation
    private static final String[] VALID_BLOOD_GROUPS =
            {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    // Blood group validator
    private static void validateBloodGroup(String bg)
            throws InvalidBloodGroupException {
        for (String valid : VALID_BLOOD_GROUPS) {
            if (valid.equalsIgnoreCase(bg)) return;
        }
        throw new InvalidBloodGroupException(bg);
    }

    // ID validator
    private static int parseId(String input, String field)
            throws InvalidInputException {
        try {
            int id = Integer.parseInt(input.trim());
            if (id <= 0) throw new InvalidInputException(
                    field + " must be a positive number");
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    field + " must be a valid number");
        }
    }

    public static void main(String[] args) throws Exception {

        // --- Fix Eclipse console buffering ----------------------------
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        System.setOut(ps);
        System.setErr(ps);  // merges Hibernate logs into same stream as menu

        // --- 1. Initialize Hibernate ----------------------------------
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // --- 2. Service implementations -------------------------------
        DonorServiceImpl          donorService        = new DonorServiceImpl();
        RecipientServiceImpl      recipientService    = new RecipientServiceImpl();
        BloodBankServiceImpl      bloodBankService    = new BloodBankServiceImpl();
        BloodRequestServiceImpl   bloodRequestService = new BloodRequestServiceImpl();
        DonationServiceImpl       donationService     = new DonationServiceImpl();
        BloodInventoryServiceImpl inventoryService    = new BloodInventoryServiceImpl();
        HealthCheckServiceImpl    healthCheckService  = new HealthCheckServiceImpl();

        // --- 3. Single Scanner for entire app -------------------------
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        // --- 4. Main menu loop ----------------------------------------
        do {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("    ONLINE BLOOD DONATION SYSTEM         ");
            System.out.println("==========================================");
            System.out.println("  DONOR");
            System.out.println("   1.  Register Donor");
            System.out.println("   2.  View Donor by ID");
            System.out.println("   3.  View All Donors");
            System.out.println("   4.  Update Donor");
            System.out.println("   5.  Delete Donor");
            System.out.println("------------------------------------------");
            System.out.println("  RECIPIENT");
            System.out.println("   6.  Register Recipient");
            System.out.println("   7.  View Recipient by ID");
            System.out.println("   8.  View All Recipients");
            System.out.println("   9.  Update Recipient");
            System.out.println("  10.  Delete Recipient");
            System.out.println("------------------------------------------");
            System.out.println("  BLOOD BANK");
            System.out.println("  11.  Add Blood Bank");
            System.out.println("  12.  View Blood Bank by ID");
            System.out.println("  13.  View All Blood Banks");
            System.out.println("  14.  Update Blood Bank");
            System.out.println("  15.  Delete Blood Bank");
            System.out.println("------------------------------------------");
            System.out.println("  BLOOD REQUEST");
            System.out.println("  16.  Create Blood Request");
            System.out.println("  17.  View Blood Request by ID");
            System.out.println("  18.  View All Blood Requests");
            System.out.println("  19.  Update Blood Request Status");
            System.out.println("  20.  Delete Blood Request");
            System.out.println("------------------------------------------");
            System.out.println("  DONATION");
            System.out.println("  21.  Record Donation");
            System.out.println("  22.  View Donation by ID");
            System.out.println("  23.  View All Donations");
            System.out.println("  24.  Update Donation");
            System.out.println("  25.  Delete Donation");
            System.out.println("------------------------------------------");
            System.out.println("  BLOOD INVENTORY");
            System.out.println("  26.  Add Blood Inventory");
            System.out.println("  27.  View Inventory by ID");
            System.out.println("  28.  View All Inventories");
            System.out.println("  29.  Update Inventory Quantity");
            System.out.println("  30.  Delete Inventory Record");
            System.out.println("------------------------------------------");
            System.out.println("  HEALTH CHECK");
            System.out.println("  31.  Add Health Check");
            System.out.println("  32.  View Health Check by ID");
            System.out.println("  33.  View All Health Checks");
            System.out.println("  34.  Update Health Check");
            System.out.println("  35.  Delete Health Check");
            System.out.println("------------------------------------------");
            System.out.println("   0.  Exit");
            System.out.println("==========================================");
            System.out.print("Enter choice: ");
            System.out.flush();

            try {
                String input = sc.nextLine().trim();

                if (input.isEmpty())
                    throw new InvalidInputException("Choice cannot be empty");

                choice = Integer.parseInt(input);

                switch (choice) {

                    // -- Donor -----------------------------------------
                    case 1: {
                        donorService.saveDonor(sf);
                        break;
                    }
                    case 2: {
                        System.out.print("Enter Donor ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donor ID");
                        Donor donor = donorService.getDonor(sf, id);
                        if (donor == null) throw new DonorNotFoundException(id);
                        System.out.println(donor);
                        break;
                    }
                    case 3: {
                        List<Donor> donors = donorService.getAllDonors(sf);
                        if (donors.isEmpty())
                            System.out.println("No donors registered yet.");
                        else
                            donors.forEach(System.out::println);
                        break;
                    }
                    case 4: {
                        System.out.print("Enter Donor ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donor ID");
                        Donor donor = donorService.getDonor(sf, id);
                        if (donor == null) throw new DonorNotFoundException(id);
                        donorService.updateDonor(sf, id);
                        break;
                    }
                    case 5: {
                        System.out.print("Enter Donor ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donor ID");
                        Donor donor = donorService.getDonor(sf, id);
                        if (donor == null) throw new DonorNotFoundException(id);
                        donorService.deleteDonor(sf, id);
                        break;
                    }

                    // -- Recipient -------------------------------------
                    case 6: {
                        recipientService.saveRecipient(sf);
                        break;
                    }
                    case 7: {
                        System.out.print("Enter Recipient ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Recipient ID");
                        Recipient recipient = recipientService.getRecipient(sf, id);
                        if (recipient == null) throw new RecipientNotFoundException(id);
                        System.out.println(recipient);
                        break;
                    }
                    case 8: {
                        List<Recipient> recipients = recipientService.getAllRecipients(sf);
                        if (recipients.isEmpty())
                            System.out.println("No recipients registered yet.");
                        else
                            recipients.forEach(System.out::println);
                        break;
                    }
                    case 9: {
                        System.out.print("Enter Recipient ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Recipient ID");
                        Recipient recipient = recipientService.getRecipient(sf, id);
                        if (recipient == null) throw new RecipientNotFoundException(id);
                        recipientService.updateRecipient(sf, id);
                        break;
                    }
                    case 10: {
                        System.out.print("Enter Recipient ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Recipient ID");
                        Recipient recipient = recipientService.getRecipient(sf, id);
                        if (recipient == null) throw new RecipientNotFoundException(id);
                        recipientService.deleteRecipient(sf, id);
                        break;
                    }

                    // -- Blood Bank ------------------------------------
                    case 11: {
                        bloodBankService.saveBloodBank(sf);
                        break;
                    }
                    case 12: {
                        System.out.print("Enter Blood Bank ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Bank ID");
                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
                        if (bank == null) throw new BloodBankNotFoundException(id);
                        System.out.println(bank);
                        break;
                    }
                    case 13: {
                        List<BloodBank> banks = bloodBankService.getAllBloodBanks(sf);
                        if (banks.isEmpty())
                            System.out.println("No blood banks added yet.");
                        else
                            banks.forEach(System.out::println);
                        break;
                    }
                    case 14: {
                        System.out.print("Enter Blood Bank ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Bank ID");
                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
                        if (bank == null) throw new BloodBankNotFoundException(id);
                        bloodBankService.updateBloodBank(sf, id);
                        break;
                    }
                    case 15: {
                        System.out.print("Enter Blood Bank ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Bank ID");
                        BloodBank bank = bloodBankService.getBloodBank(sf, id);
                        if (bank == null) throw new BloodBankNotFoundException(id);
                        bloodBankService.deleteBloodBank(sf, id);
                        break;
                    }

                    // -- Blood Request ---------------------------------
                    case 16: {

                        // CREATE BLOOD REQUEST
                        bloodRequestService.saveBloodRequest(sf);

                        System.out.println(
                                "\nChecking Blood Group Match..."
                        );

                        // GET ALL DONORS
                        List<Donor> donors =
                                donorService.getAllDonors(sf);

                        // GET ALL BLOOD REQUESTS
                        List<BloodRequest> requests =
                                bloodRequestService.getAllBloodRequests(sf);

                        // CHECK IF REQUEST EXISTS
                        if (requests.isEmpty()) {

                            System.out.println(
                                    "No Blood Requests Found"
                            );

                            break;
                        }

                        // GET LATEST REQUEST
                        BloodRequest latestRequest =
                                requests.get(requests.size() - 1);

                        boolean matchFound = false;

                        // CHECK MATCHING DONORS
                        for (Donor donor : donors) {

                            if (donor.getBloodGroup()
                                    .equalsIgnoreCase(
                                            latestRequest.getBloodGroup())) {

                                System.out.println(
                                        "\n===== MATCH FOUND ====="
                                );

                                System.out.println(
                                        "Recipient Required Blood Group : "
                                                + latestRequest.getBloodGroup()
                                );

                                System.out.println(
                                        "Donor ID : "
                                                + donor.getId()
                                );

                                System.out.println(
                                        "Donor Name : "
                                                + donor.getName()
                                );

                                System.out.println(
                                        "Donor Blood Group : "
                                                + donor.getBloodGroup()
                                );

                                System.out.println(
                                        "Contact Number : "
                                                + donor.getContactNumber()
                                );

                                System.out.println(
                                        "Address : "
                                                + donor.getAddress()
                                );

                                System.out.println(
                                        "Email : "
                                                + donor.getEmail()
                                );

                                System.out.println(
                                        "Checking Donor Eligibility..."
                                );

                                // CHECK HEALTH STATUS
                                List<HealthCheck> checks =
                                        healthCheckService.getAllHealthChecks(sf);

                                boolean eligible = false;

                                for (HealthCheck hc : checks) {

                                    if (hc.getDonor().getId()
                                            .equals(donor.getId())
                                            &&
                                            hc.getIsEligible()
                                                    .equalsIgnoreCase("Yes")) {

                                        eligible = true;

                                        break;
                                    }
                                }

                                if (eligible) {

                                    System.out.println(
                                            "Donor Eligible for Donation"
                                    );

                                    System.out.println(
                                            "Searching Blood Inventory..."
                                    );

                                    // CHECK INVENTORY
                                    List<BloodInventory> inventories =
                                            inventoryService.getAllBloodInventories(sf);

                                    boolean inventoryFound = false;

                                    for (BloodInventory inv : inventories) {

                                        if (inv.getBloodGroup()
                                                .equalsIgnoreCase(
                                                        latestRequest.getBloodGroup())
                                                &&
                                                inv.getQuantity() > 0) {

                                            inventoryFound = true;

                                            System.out.println(
                                                    "Blood Available in Inventory"
                                            );

                                            System.out.println(
                                                    "Available Quantity : "
                                                            + inv.getQuantity()
                                                            + " ml"
                                            );

                                            System.out.println(
                                                    "Blood Request Approved"
                                            );

                                            System.out.println(
                                                    "================================="
                                            );

                                            break;
                                        }
                                    }

                                    if (!inventoryFound) {

                                        System.out.println(
                                                "❌ Blood Not Available in Inventory"
                                        );
                                    }

                                } else {

                                    System.out.println(
                                            "❌ Donor Not Eligible for Donation"
                                    );
                                }

                                matchFound = true;

                                break;
                            }
                        }

                        // IF NO MATCH FOUND
                        if (!matchFound) {

                            System.out.println(
                                    "\n❌ NO MATCHING DONOR FOUND"
                            );
                        }

                        break;
                    }
                    case 17: {
                        System.out.print("Enter Blood Request ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Request ID");
                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
                        if (request == null) throw new BloodRequestNotFoundException(id);
                        System.out.println(request);
                        break;
                    }
                    case 18: {
                        List<BloodRequest> requests = bloodRequestService.getAllBloodRequests(sf);
                        if (requests.isEmpty())
                            System.out.println("No blood requests found.");
                        else
                            requests.forEach(System.out::println);
                        break;
                    }
                    case 19: {
                        System.out.print("Enter Blood Request ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Request ID");
                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
                        if (request == null) throw new BloodRequestNotFoundException(id);
                        bloodRequestService.updateBloodRequest(sf, id);
                        break;
                    }
                    case 20: {
                        System.out.print("Enter Blood Request ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Blood Request ID");
                        BloodRequest request = bloodRequestService.getBloodRequest(sf, id);
                        if (request == null) throw new BloodRequestNotFoundException(id);
                        bloodRequestService.deleteBloodRequest(sf, id);
                        break;
                    }

                    // -- Donation --------------------------------------
                    case 21: {
                        donationService.saveDonation(sf);
                        break;
                    }
                    case 22: {
                        System.out.print("Enter Donation ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donation ID");
                        Donation donation = donationService.getDonation(sf, id);
                        if (donation == null) throw new DonationNotFoundException(id);
                        System.out.println(donation);
                        break;
                    }
                    case 23: {
                        List<Donation> donations = donationService.getAllDonations(sf);
                        if (donations.isEmpty())
                            System.out.println("No donations recorded yet.");
                        else
                            donations.forEach(System.out::println);
                        break;
                    }
                    case 24: {
                        System.out.print("Enter Donation ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donation ID");
                        Donation donation = donationService.getDonation(sf, id);
                        if (donation == null) throw new DonationNotFoundException(id);
                        donationService.updateDonation(sf, id);
                        break;
                    }
                    case 25: {
                        System.out.print("Enter Donation ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Donation ID");
                        Donation donation = donationService.getDonation(sf, id);
                        if (donation == null) throw new DonationNotFoundException(id);
                        donationService.deleteDonation(sf, id);
                        break;
                    }

                    // -- Blood Inventory -------------------------------
                    case 26: {
                        inventoryService.saveBloodInventory(sf);
                        break;
                    }
                    case 27: {
                        System.out.print("Enter Inventory ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Inventory ID");
                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
                        if (inv == null) throw new BloodInventoryNotFoundException(id);
                        System.out.println(inv);
                        break;
                    }
                    case 28: {
                        List<BloodInventory> inventories = inventoryService.getAllBloodInventories(sf);
                        if (inventories.isEmpty())
                            System.out.println("No inventory records found.");
                        else
                            inventories.forEach(System.out::println);
                        break;
                    }
                    case 29: {
                        System.out.print("Enter Inventory ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Inventory ID");
                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
                        if (inv == null) throw new BloodInventoryNotFoundException(id);
                        inventoryService.updateBloodInventory(sf, id);
                        break;
                    }
                    case 30: {
                        System.out.print("Enter Inventory ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Inventory ID");
                        BloodInventory inv = inventoryService.getBloodInventory(sf, id);
                        if (inv == null) throw new BloodInventoryNotFoundException(id);
                        inventoryService.deleteBloodInventory(sf, id);
                        break;
                    }

                    // -- Health Check ----------------------------------
                    case 31: {
                        healthCheckService.saveHealthCheck(sf);
                        break;
                    }
                    case 32: {
                        System.out.print("Enter Health Check ID: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Health Check ID");
                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
                        if (hc == null) throw new HealthCheckNotFoundException(id);
                        System.out.println(hc);
                        break;
                    }
                    case 33: {
                        List<HealthCheck> checks = healthCheckService.getAllHealthChecks(sf);
                        if (checks.isEmpty())
                            System.out.println("No health check records found.");
                        else
                            checks.forEach(System.out::println);
                        break;
                    }
                    case 34: {
                        System.out.print("Enter Health Check ID to Update: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Health Check ID");
                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
                        if (hc == null) throw new HealthCheckNotFoundException(id);
                        healthCheckService.updateHealthCheck(sf, id);
                        break;
                    }
                    case 35: {
                        System.out.print("Enter Health Check ID to Delete: ");
                        System.out.flush();
                        int id = parseId(sc.nextLine(), "Health Check ID");
                        HealthCheck hc = healthCheckService.getHealthCheck(sf, id);
                        if (hc == null) throw new HealthCheckNotFoundException(id);
                        healthCheckService.deleteHealthCheck(sf, id);
                        break;
                    }

                    // -- Exit ------------------------------------------
                    case 0:
                        System.out.println("\nThank you for using the Online Blood Donation System.");
                        System.out.println("Every drop counts. You save a life.");
                        System.out.flush();
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 35.");
                }

            } catch (DonorNotFoundException e) {
                System.out.println("ERROR - Donor: " + e.getMessage());

            } catch (RecipientNotFoundException e) {
                System.out.println("ERROR - Recipient: " + e.getMessage());

            } catch (BloodBankNotFoundException e) {
                System.out.println("ERROR - Blood Bank: " + e.getMessage());

            } catch (BloodRequestNotFoundException e) {
                System.out.println("ERROR - Blood Request: " + e.getMessage());

            } catch (DonationNotFoundException e) {
                System.out.println("ERROR - Donation: " + e.getMessage());

            } catch (BloodInventoryNotFoundException e) {
                System.out.println("ERROR - Blood Inventory: " + e.getMessage());

            } catch (HealthCheckNotFoundException e) {
                System.out.println("ERROR - Health Check: " + e.getMessage());

            } catch (InvalidInputException e) {
                System.out.println("ERROR - Input: " + e.getMessage());

            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number for the menu choice.");

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (choice != 0);

        // --- 5. Cleanup -----------------------------------------------
        sf.close();
        sc.close();
    }
}


