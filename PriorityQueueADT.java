/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloodtestscheduler;

/**
 *
 * @author David Constantin
 */
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.*;
import javax.swing.JOptionPane;

public class PriorityQueueADT {
    private PriorityQueue<Patient> queue;
    
    public PriorityQueueADT() {
        queue = new PriorityQueue<>(Comparator.comparingInt(Patient::getEffectivePriority));
    }
    
    //enqueing
    
    public void enqueue(String name, String priority, String gpDetails, int age, boolean fromHospital) {
        queue.add(new Patient(name, priority, gpDetails, age, fromHospital));
    }
    
    //dequeue
    public String dequeue() {
        if (!queue.isEmpty()) {
            return queue.poll().name;
        }
        return "No patients in queue";
    }
    
    //check the queue 
    public String peek() {
        if (!queue.isEmpty()) {
            return queue.peek().name;
        }
        return "No patients in queue";
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    //FILTERING >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //filter by highest priority
    public List<Patient> getHighestPriorityPatients() {
        List<Patient> sortedList = new ArrayList<>(queue);
        sortedList.sort(Comparator.comparingInt(Patient::getEffectivePriority)); // Sort by priority
    return sortedList;
}

        //filter by age
    public List<Patient> filterByAge(int minAge) {
        List<Patient> filteredList = new ArrayList<>();
        for (Patient p : queue) {
            if (p.age >= minAge) {
                filteredList.add(p);
            }
        }
        return filteredList;
    }
    //filter by hospital stattus
    public List<Patient> filterByHospitalStatus(boolean fromHospital) {
        List<Patient> filteredList = new ArrayList<>();
        for (Patient p : queue) {
            if (p.fromHospital == fromHospital) {
                filteredList.add(p);
            }
        }
        return filteredList;
    }
    
    
    //tried to get this to work but couldn't in the end, decided to manually have it via user input since it met the criteria for displaying the last 5 anyway
    
    /*
    public Patient getNextPatientWithConfirmation(MissedAppointments missedAppointments) {
        if (!queue.isEmpty()) {
            Patient nextPatient = queue.poll(); // Remove the patient from the queue
            int response = JOptionPane.showConfirmDialog(null, "Did " + nextPatient.name + " show up?", "Appointment Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.NO_OPTION) {
                missedAppointments.addMissed(nextPatient.name);
            }
            
            return nextPatient;
        }
        return null;
    }
   
    
    public void addMissedPatientManually(String name) {
    missedAppointments.addMissed(name);
}
  */
    //remove a patient
    public void removePatient(Patient patient) {
        queue.remove(patient);
    }
   
}


