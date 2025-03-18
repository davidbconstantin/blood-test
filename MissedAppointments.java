package bloodtestscheduler;

/**
 * @author David Constantin
 */

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextArea;

// Stack to track last 5 missed appointments
class MissedAppointments {
    private LinkedList<String> missedList;
    private JTextArea outputArea; // gui output area
    
    public MissedAppointments(JTextArea outputArea) {
        this.missedList = new LinkedList<>();
        this.outputArea = outputArea;
    }
    //get missed list
    public List<String> getMissedList() {
        return missedList;
    }
    //add the missed name ot the list
    public void addMissed(String name) {
        if (missedList.size() == 5) {
            missedList.removeFirst();
        }
        missedList.add(name);
        updateGUI();
    }
    //display missed name ot the list only 5
    public void displayMissed() {
        outputArea.append("Last 5 missed appointments:\n");
        for (String name : missedList) {
            outputArea.append(name + "\n");
        }
    }
    //update the graphic interface to let the user know
    private void updateGUI() {
        outputArea.setText("Last 5 missed appointments:\n");
        for (String name : missedList) {
            outputArea.append(name + "\n");
        }
    }
}