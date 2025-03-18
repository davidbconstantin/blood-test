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
        
        // uncomment to test the tests
       /* 
        
        System.out.println("Starting Blood Test Scheduler Tests...\n");

  // Test 1: addding patients ------------------------------------------------------------------
        System.out.println("Test 1: Adding patients...");
        queue.enqueue("David Bulugea", "Urgent", "Dr. Smith", 45, false);
        queue.enqueue("Hamilton Niculescu", "Medium", "Dr. Brown", 30, true);
        queue.enqueue("Slava M.", "Low", "Dr. White", 65, false);
        System.out.println("Patients Added Successfully\n");

        // Test 2: applpying priority filtering
        System.out.println("Test 2: Filtering Patients by Priority...");
        List<Patient> urgentPatients = queue.getHighestPriorityPatients();
        int urgentCount = (int) urgentPatients.stream().filter(p -> p.priority.equals("Urgent")).count();
        System.out.println("Expected: 1 patient, Found: " + urgentCount);
        assert urgentCount == 1 : "Filtering by priority failed";
        System.out.println("Filtering by priority works correctly\n");
          
        // Test 3: applying filering by age------------------------------------------------------------------
        System.out.println("Test 3: Filtering Patients by Age...");
        int seniorCount = queue.filterByAge(65).size();
        System.out.println("Expected: 1 patient, Found: " + seniorCount);
        assert seniorCount == 1 : "Filtering by age failed";
        System.out.println("Filtering by age works correctly\n");

        // Test 4: applpying hospital status filtering------------------------------------------------------------------
        System.out.println("Test 4: Filtering Patients by Hospital Status...");
        int hospitalCount = queue.filterByHospitalStatus(true).size();
        System.out.println("Expected: 1 patient, Found: " + hospitalCount);
        assert hospitalCount == 1 : "Filtering by hospital status failed";
        System.out.println("Filtering by hospital status works correctly\n");
          
        // Tests completed ------------------------------------------------------------------
        System.out.println("All Tests have been completed. GUI will now run ...");
*/

    }
}
