package G39_CS3330_Assignment_5.animal_manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

class ShelterView {
	int sort = 0;
	JButton button = new JButton("Name Sort");
	JButton ageButton = new JButton("Age Sort");
	JButton specButton = new JButton("Species Sort");
	JButton adoptButton = new JButton("Adopt");
	JButton addButton = new JButton("Add New");
	JButton remButton = new JButton("Remove");
	JTextField name = new JTextField("");
	JTextField age = new JTextField("");
	JTextField type = new JTextField("");
	JTextField species = new JTextField("");
	JLabel adoptLabel = new JLabel("");
	JList list;
	private JFrame frame = new JFrame();
	private DefaultListModel<String> listModel;
	public void initShelterView(List<Pet> shelterList) {
		// Creating instance of JFrame
		listModel = new DefaultListModel<>();
		
		for(int i = 0; i < shelterList.size(); i++) {
			listModel.add(i, shelterList.get(i).getName());
		}
		
		list = new JList<>(listModel);		
        
        list.setBounds(50, 50, 200, 300);
        
        frame.add(list);
        
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel speciesLabel = new JLabel("Species:");
        nameLabel.setBounds(280, 50, 100, 25);
        ageLabel.setBounds(465, 50, 100, 25);
        typeLabel.setBounds(287, 110, 100, 25);
        speciesLabel.setBounds(442, 110, 100, 25);
        adoptLabel.setBounds(300, 165, 75, 25);
        name.setBounds(325, 50, 100, 25);
        age.setBounds(500, 50, 25, 25);
        type.setBounds(325, 110, 100, 25);
        species.setBounds(500, 110, 115, 25);
        frame.add(nameLabel);
        frame.add(ageLabel);
        frame.add(typeLabel);
        frame.add(speciesLabel);
        frame.add(adoptLabel);
        frame.add(name);
        frame.add(age);
        frame.add(type);
        frame.add(species);
        // x axis, y axis, width, height
        adoptButton.setBounds(500, 165, 85, 25);
        addButton.setBounds(500, 195, 85, 25);
        remButton.setBounds(500, 225, 85, 25);
        button.setBounds(50, 355, 200, 30);
        ageButton.setBounds(50, 390, 200, 30);
        specButton.setBounds(50, 425, 200, 30);
        
        list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) { 
				int index = list.getSelectedIndex();
				if(index > -1 && index < shelterList.size()) {
					name.setText(shelterList.get(index).getName());
					age.setText(Integer.toString(shelterList.get(index).getAge()));
					type.setText(shelterList.get(index).getType());
					species.setText(shelterList.get(index).getSpecies());
					if(shelterList.get(index).getAdopted() == false) {
						adoptLabel.setText("Adopt Me!");
						adoptLabel.setForeground(Color.GREEN);
					}
					else {
						adoptLabel.setText("Not Available");
						adoptLabel.setForeground(Color.RED);
					}
				}
				else {
					name.setText("");
					age.setText("");
					type.setText("");
					species.setText("");
					adoptLabel.setText("");
				}
			}
        });
        
        // adding button in JFrame
        frame.add(button);
        frame.add(adoptButton);
        frame.add(addButton);
        frame.add(remButton);
        frame.add(ageButton);
        frame.add(specButton);

        // 400 width and 500 height
        frame.setSize(800, 500);
        

        // using no layout managers
        frame.setLayout(null);

        // making the frame visible
        frame.setVisible(true);
        
		
		
	}
	
	
	
	public void updateList(List<Pet> shelterList) {
		listModel.clear();
		for(int i = 0; i < shelterList.size(); i++) {
			listModel.add(i, shelterList.get(i).getName());
		}
	}
}
