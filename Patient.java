/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloodtestscheduler;

/**
 *
 * @author David Constantin
 */
// patient Class
class Patient {
    String name;
    String priority;
    String gpDetails;
    int age;
    boolean fromHospital;
    
    //constructro
    public Patient(String name, String priority, String gpDetails, int age, boolean fromHospital) {
        this.name = name;
        this.priority = priority;
        this.gpDetails = gpDetails;
        this.age = age;
        this.fromHospital = fromHospital;
    }
    //get the effective priority by numbered approach
    public int getEffectivePriority() {
        int priorityValue = switch (priority.toLowerCase()) {
            case "urgent" -> 1;
            case "medium" -> 2;
            case "low" -> 3;
            default -> 4;
        };
        return priorityValue - (fromHospital ? 2 : 0) - (age > 65 ? 1 : 0); // addjust priority based on age and hospital status
    }
}
