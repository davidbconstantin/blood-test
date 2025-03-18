package bloodtestscheduler;

import java.util.List;

/**
 * @author David Constantin
 */

public class Main {
    public static void main(String[] args) {
        
        // GUI instance
        BloodTestSchedulerGUI gui = new BloodTestSchedulerGUI();
        
        // Test instance for queue
        PriorityQueueADT queue = new PriorityQueueADT();
        MissedAppointments missedAppointments = new MissedAppointments(null); // Assuming GUI handles output
        
        // Uncomment to run tests
        
        
        System.out.println("Starting Blood Test Scheduler Tests...\n");

        // Test 1: Adding Patients ------------------------------------------------------------------
        System.out.println("Test 1: Adding Patients...");
        queue.enqueue("David Bulugea", "Urgent", "Dr. Smith", 45, false);
        queue.enqueue("Hamilton Niculescu", "Medium", "Dr. Brown", 30, true);
        queue.enqueue("Slava M.", "Low", "Dr. White", 65, false);
        System.out.println("Patients Added Successfully\n");

        // Test 2: Applying Priority Filtering
        System.out.println("Test 2: Filtering Patients by Priority...");
        List<Patient> urgentPatients = queue.getHighestPriorityPatients();
        int urgentCount = (int) urgentPatients.stream().filter(p -> p.priority.equals("Urgent")).count();
        System.out.println("Expected: 1 patient, Found: " + urgentCount);
        assert urgentCount == 1 : "Filtering by priority failed";
        System.out.println("Filtering by priority works correctly\n");
          
        // Test 3: Applying Filtering by Age ------------------------------------------------------------------
        System.out.println("Test 3: Filtering Patients by Age...");
        int seniorCount = queue.filterByAge(65).size();
        System.out.println("Expected: 1 patient, Found: " + seniorCount);
        assert seniorCount == 1 : "Filtering by age failed";
        System.out.println("Filtering by age works correctly\n");

        // Test 4: Applying Hospital Status Filtering ------------------------------------------------------------------
        System.out.println("Test 4: Filtering Patients by Hospital Status...");
        int hospitalCount = queue.filterByHospitalStatus(true).size();
        System.out.println("Expected: 1 patient, Found: " + hospitalCount);
        assert hospitalCount == 1 : "Filtering by hospital status failed";
        System.out.println("Filtering by hospital status works correctly\n");
        
        // Test 5: Missed Appointments Tracking ------------------------------------------------------------------
        System.out.println("Test 5: Missed Appointments Tracking...");
        missedAppointments.addMissed("David Bulugea");
        missedAppointments.addMissed("Cristiano Ronaldo");
        missedAppointments.addMissed("Lionel Messi");
        missedAppointments.addMissed("St. Patrick");
        missedAppointments.addMissed("Vlad Tepes");
        missedAppointments.addMissed("King Ferdinand"); // should remove the first one == david

        List<String> missedList = missedAppointments.getMissedList();
        System.out.println("Expected: 5 patients in list, Found: " + missedList.size());
        assert missedList.size() == 5 : "Missed appointments tracking failed";
        assert !missedList.contains("David Bulugea") : "Missed appointments list is not updating correctly";
        System.out.println("Missed Appointments Tracking works correctly\n");
           
        // Test 6: valid input for age check ------------------------------------------------------------------
        System.out.println("Test 6: Valid Input Check (Age must be a number)...");
        String validAgeInput = "45";
        String invalidAgeInput = "forty-five";

        boolean isValid = isNumeric(validAgeInput);
        boolean isInvalid = isNumeric(invalidAgeInput);

        System.out.println("Expected: true for a numeric age input, Found: " + isValid);
        System.out.println("Expected: false for a non numeric age input, Found: " + isInvalid);
        assert isValid : "Valid numeric age input failed";
        assert !isInvalid : "Invalid non-numeric age input failed";
        System.out.println("Age validation works correctly\n");

        // Tests completed ------------------------------------------------------------------
        System.out.println("All Tests have been completed. GUI will now run ...");

        

    }

    // check if string is numeric
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
