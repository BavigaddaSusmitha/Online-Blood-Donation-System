package com.blooddonation.businesslogic;

import java.util.List;

import com.blooddonation.entities.BloodInventory;
import com.blooddonation.entities.BloodRequest;
import com.blooddonation.entities.Donor;
import com.blooddonation.entities.HealthCheck;

public class BloodDonationBusinessLogic {

    // ✅ CHECK BLOOD GROUP MATCH
    public static boolean isBloodGroupMatch(
            Donor donor,
            BloodRequest request) {

        return donor.getBloodGroup()
                .equalsIgnoreCase(
                        request.getBloodGroup()
                );
    }

    // ✅ CHECK DONOR ELIGIBILITY
    public static boolean isDonorEligible(
            HealthCheck healthCheck) {

        return healthCheck.getIsEligible()
                .equalsIgnoreCase("Yes");
    }

    // ✅ CHECK INVENTORY AVAILABILITY
    public static boolean isBloodAvailable(
            BloodInventory inventory,
            int requiredQuantity) {

        return inventory.getQuantity()
                >= requiredQuantity;
    }

    // ✅ UPDATE INVENTORY AFTER BLOOD ISSUE
    public static void updateInventory(
            BloodInventory inventory,
            int issuedQuantity) {

        inventory.setQuantity(
                inventory.getQuantity()
                        - issuedQuantity
        );

        System.out.println(
                "\n===== BLOOD INVENTORY UPDATED ====="
        );

        System.out.println(
                "Blood Group : "
                        + inventory.getBloodGroup()
        );

        System.out.println(
                "Remaining Quantity : "
                        + inventory.getQuantity()
                        + " ml"
        );

        System.out.println(
                "==================================="
        );
    }

    // ✅ DISPLAY MATCH FOUND MESSAGE
    public static void displayMatchFound(
            Donor donor,
            BloodRequest request) {

        System.out.println(
                "\n===== MATCH FOUND ====="
        );

        System.out.println(
                "Recipient Required Group : "
                        + request.getBloodGroup()
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
                "================================"
        );
    }

    // ✅ DISPLAY NO MATCH MESSAGE
    public static void displayNoMatch() {

        System.out.println(
                "\n❌ NO MATCHING DONOR FOUND"
        );
    }

    // ✅ FIND MATCHING DONOR
    public static Donor findMatchingDonor(
            List<Donor> donors,
            BloodRequest request) {

        for (Donor donor : donors) {

            if (donor.getBloodGroup()
                    .equalsIgnoreCase(
                            request.getBloodGroup())) {

                return donor;
            }
        }

        return null;
    }
}