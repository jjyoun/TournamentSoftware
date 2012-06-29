package controller;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.URL;
import java.util.Collection;
import model.Participant;
 
public class ListDisplayAndEntry extends JPanel 
                               implements ListSelectionListener {
    private JList list;
    private JScrollPane jScrollPane;
    private DefaultListModel listModel;
 
    private static final String addString = "Add Participant";
    private static final String editString = "Edit Participant";
    private static final String deleteString = "Delete Participant";
   
 
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    
    private JDialog dialog;
 
    
    private JTextArea log;
    static private String newline = "\n";
 
    public ListDisplayAndEntry(Collection<Participant> listOfParticipants, JPanel jPanel, JDialog dialog) {
        super(null);
        
        this.dialog = dialog;
 
        //Create and populate the list model.
        listModel = new DefaultListModel();
        for (Participant p : listOfParticipants)
            listModel.addElement(p);
        listModel.addListDataListener(new MyListDataListener());
 
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setBounds(0, 40, 120, 350);
        list.setBorder(BorderFactory.createBevelBorder(1));
        list.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        jScrollPane = new JScrollPane(list);
        jScrollPane.setBounds(0,40,140,330);
        
        //Create the list-modifying buttons.
        addButton = new JButton(addString);
        addButton.setBounds(140,40,150,30);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddButtonListener());
 
        editButton = new JButton(editString);
        editButton.setBounds(140,80,150,30);
        editButton.setActionCommand(addString);
        editButton.addActionListener(new EditButtonListener());
        
        deleteButton = new JButton(deleteString);
        deleteButton.setBounds(140,120,150,30);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(
            new DeleteButtonListener());
 
        jPanel.add(jScrollPane);
        jPanel.add(addButton);
        jPanel.add(editButton);
        jPanel.add(deleteButton);
        
 
        
    }
 
    class MyListDataListener implements ListDataListener {
        public void contentsChanged(ListDataEvent e) {
            log.append("contentsChanged: " + e.getIndex0() +
                       ", " + e.getIndex1() + newline); 
            log.setCaretPosition(log.getDocument().getLength());
        }
        public void intervalAdded(ListDataEvent e) {
            log.append("intervalAdded: " + e.getIndex0() +
                       ", " + e.getIndex1() + newline); 
            log.setCaretPosition(log.getDocument().getLength());
        }
        public void intervalRemoved(ListDataEvent e) {
            log.append("intervalRemoved: " + e.getIndex0() +
                       ", " + e.getIndex1() + newline); 
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
 
    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            /* 
             * This method can be called only if
             * there's a valid selection,
             * so go ahead and remove whatever's selected.
             */
 
            ListSelectionModel lsm = list.getSelectionModel();
            int firstSelected = lsm.getMinSelectionIndex();
            int lastSelected = lsm.getMaxSelectionIndex();
            listModel.removeRange(firstSelected, lastSelected);
 
            int size = listModel.size();
 
            if (size == 0) {
            //List is empty: disable delete, up, and down buttons.
                deleteButton.setEnabled(false);
                
 
            } else {
            //Adjust the selection.
                if (firstSelected == listModel.getSize()) {
                //Removed item in last position.
                    firstSelected--;
                }
                list.setSelectedIndex(firstSelected);
            }
        }
    }
 
    
    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dialog.setVisible(true);
        }
    }
    class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        }
    }
 
    
 
    //Listener method for list selection changes.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
 
            if (list.getSelectedIndex() == -1) {
            //No selection: disable delete and edit buttons.
                addButton.setEnabled(true);
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
            } else {
            //Single selection: permit all operations.
                deleteButton.setEnabled(true);
                deleteButton.setEnabled(true);
                editButton.setEnabled(true);
            }
        }
    }
   
}