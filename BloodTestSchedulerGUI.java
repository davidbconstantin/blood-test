/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloodtestscheduler;

/**
 *
 * @author David Constantin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GUI for Blood Test Scheduler

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// GUI for Blood Test Scheduler
class BloodTestSchedulerGUI {
    // GUI components
    private JFrame frame;
    private JTextField nameField, gpField, ageField;
    private JComboBox<String> priorityBox, filterBox;
    private JCheckBox hospitalCheck;
    private JTextArea outputArea;
    private JLabel queueCountLabel;
    
    // Core logic components
    private PriorityQueueADT queue;
    private MissedAppointments missedAppointments;
    
    
    public BloodTestSchedulerGUI() {
    queue = new PriorityQueueADT();
    
    //start of GUI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    frame = new JFrame("Blood Test Scheduler");
    frame.setSize(550, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Blood Test Scheduler", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(titleLabel, BorderLayout.NORTH);

    //JPanel
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(9, 2, 10, 10));

    //inputt fields------------------------------------------------------------------
    panel.add(new JLabel("Name:"));
    nameField = new JTextField();
    panel.add(nameField);

    panel.add(new JLabel("Priority:"));
    priorityBox = new JComboBox<>(new String[]{"Urgent", "Medium", "Low"});
    panel.add(priorityBox);

    panel.add(new JLabel("Age:"));
    ageField = new JTextField();
    panel.add(ageField);

    panel.add(new JLabel("GP Details:"));
    gpField = new JTextField();
    panel.add(gpField);

    panel.add(new JLabel("From Hospital Ward?")); //true false storage 
    hospitalCheck = new JCheckBox();
    panel.add(hospitalCheck);

    //filter by
    
    panel.add(new JLabel("Filter By:"));
    filterBox = new JComboBox<>(new String[]{"Priority", "Age 65+", "From Hospital Ward"});
    panel.add(filterBox);

    // Buttons------------------------------------------------------------------
    JButton addButton = new JButton("Add Patient");
    panel.add(addButton);
    addButton.addActionListener(e -> addPatient());

    JButton nextButton = new JButton("Next Patient");
    panel.add(nextButton);
    nextButton.addActionListener(e -> nextPatient());

    JButton viewMissedButton = new JButton("View Last Missed Appointments");
    panel.add(viewMissedButton);
    viewMissedButton.addActionListener(e -> viewLastMissed());

    JButton addMissedButton = new JButton("Add Missed Appointment");
    panel.add(addMissedButton);
    addMissedButton.addActionListener(e -> addMissedPatient());

    //layout
    frame.add(panel, BorderLayout.CENTER);

    // Output Area------------------------------------------------------------------
    outputArea = new JTextArea(10, 20);
    outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

    // Initialize MissedAppointments to work with GUI
    missedAppointments = new MissedAppointments(outputArea);

    // Visibility
    frame.setVisible(true);
         
    }
    
    //end of GUI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    
//methods--------------------------------------------------------------------------------------------------------------------------------------
private void addPatient() {
    
    //variables
    String name = nameField.getText().trim();
    String priority = (String) priorityBox.getSelectedItem();
    String gp = gpField.getText().trim();
    String ageText = ageField.getText().trim();
    
    // making sure fields aren'y empty
    if (name.isEmpty() || priority == null || priority.isEmpty() || gp.isEmpty() || ageText.isEmpty()) {
        outputArea.append("Error: All fields must be filled before adding a patient.\n");
        return;
    }

    try {
        int age = Integer.parseInt(ageText);
        boolean fromHospital = hospitalCheck.isSelected();
        
        queue.enqueue(name, priority, gp, age, fromHospital);
        outputArea.append("Added: " + name + " (Priority: " + priority + ")\n");
        
        // Reset input fields
        nameField.setText("");
        ageField.setText("");
        gpField.setText("");
        hospitalCheck.setSelected(false);
    } catch (NumberFormatException e) {
        outputArea.append("Error: Age must be a valid number.\n");
    }
}

    
    
    // option to add a missed patient manually
    
    private void addMissedPatient() {
    String name = JOptionPane.showInputDialog(frame, "Enter the name of the missed patient:", "Add Missed Appointment", JOptionPane.QUESTION_MESSAGE);
    if (name != null && !name.trim().isEmpty()) {
        missedAppointments.addMissed(name); // 
        outputArea.append("Added to missed appointments: " + name + "\n");
    } else {
        JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    //filter next patient by the specific thing you want selected, pass info through
    
    private void nextPatient() {
    String filter = (String) filterBox.getSelectedItem();
    List<Patient> filteredList = null;

    // Apply filtering: Highest priority patient first
    if (filter.equals("Priority")) {
        filteredList = queue.getHighestPriorityPatients(); //returns highest priority patient
    } else if (filter.equals("Age 65+")) {
        filteredList = queue.filterByAge(65);
    } else if (filter.equals("From Hospital Ward")) {
        filteredList = queue.filterByHospitalStatus(true);
    }

    // select and remove highest priority
    if (filteredList != null && !filteredList.isEmpty()) {
        Patient next = filteredList.get(0); // get highest-priority patient
        queue.removePatient(next); // remove queue
        outputArea.append("Next: " + next.name + "\n");
    } else {
        
        //if none found then error
        outputArea.append("No patients matching the selected filter.\n");
    }
}

 //last missed
   private void viewLastMissed() {
    if (missedAppointments == null || missedAppointments.getMissedList().isEmpty()) {
        outputArea.append("No missed appointments recorded.\n");
        return;
    }
    
    outputArea.setText("Last 5 missed appointments:\n");
    for (String missed : missedAppointments.getMissedList()) {
        outputArea.append(missed + "\n");
    }
}

}


