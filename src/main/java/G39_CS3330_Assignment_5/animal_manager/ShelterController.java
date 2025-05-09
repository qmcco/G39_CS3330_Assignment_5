package G39_CS3330_Assignment_5.animal_manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

import javax.swing.JOptionPane;

import com.google.gson.*;

/**
 * class that facilitates interaction between ShelterView and Shelter by both displaying data within the Model on the View, and updating data
 * in the Model according to user actions in the View
 * is the Controller in the MVC format
 */
class ShelterController {
	private Shelter<Pet> shelter;
	private ShelterView shelterView;
	
	public ShelterController(Shelter<Pet> shelter, ShelterView shelterView) {
		this.shelter = shelter;
		this.shelterView = shelterView;
	}
	/**
	 * Initialize the Shelter by reading from the two input JSON files and storing the animals to the shelterList
	 */
	public void initShelter() {
		Gson gson = new Gson();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try(InputStream pets = classLoader.getResourceAsStream("pets.json")){
			Reader reader = new InputStreamReader(pets, "UTF-8");
			shelter.shelterList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, BasicAnimal[].class)));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try(InputStream pets = classLoader.getResourceAsStream("exotic_animals.json")){
			Reader reader = new InputStreamReader(pets, "UTF-8");
			List<ExoticAnimal> tempList = Arrays.asList(gson.fromJson(reader, ExoticAnimal[].class));
			for(int i = 0; i < tempList.size(); i++) {
				AnimalAdapter temp = new AnimalAdapter(tempList.get(i));
				Pet tempPet = Collections.max(shelter.shelterList, new Id());
				temp.setId(tempPet.getId() + 1);
				shelter.addPet(temp);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Initialize the view by calling the view module that creates the GUI elements
	 * facilitates actions on button press for all buttons within the View
	 * This includes writeButton which writes current list to a JSON file
	 * button which represents basic Name sorting of the list
	 * ageButton sorts list by age
	 * specButton sorts list by Species
	 * remButton removes the currently selected element in the list
	 * addButton adds a new pet to the list given the proper fields are filled out and valid, and that the pet is not already in the list
	 * adoptButton sets the currently selected pets status to adopted
	 * 
	 */
	public void initView() {
		shelterView.initShelterView(shelter.shelterList);
		shelterView.writeButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime curDateTime = LocalDateTime.now();
        		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        		String dateTime = curDateTime.format(format);
        		String fileName = dateTime + "_pets.json";
        		Gson builder = new GsonBuilder().setPrettyPrinting().create();
        		try(FileWriter write = new FileWriter(fileName)){
        			for(int i = 0; i < shelter.shelterList.size(); i++) {
        				builder.toJson(shelter.shelterList.get(i), write);
        			}
        		}catch(IOException g) {
        			g.printStackTrace();
        		}
        	}
        });
		shelterView.button.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		shelter.sortShelterByName();
        		sortViewList();
        	}
        });
		shelterView.ageButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		shelter.sortShelterByAge();
        		sortViewList();
        	}
        });
		shelterView.specButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		shelter.sortShelterBySpecies();
        		sortViewList();
        	}
        });
		shelterView.remButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int removed = 0;
        		for(int i = 0; i < shelter.shelterList.size(); i++) {
        			if(shelter.shelterList.get(i).getName().compareTo(shelterView.name.getText()) == 0 
        					&& shelter.shelterList.get(i).getType().compareTo(shelterView.type.getText()) == 0 
        					&& shelter.shelterList.get(i).getSpecies().compareTo(shelterView.species.getText()) == 0 
        					&& shelter.shelterList.get(i).getAge() == Integer.parseInt(shelterView.age.getText())) {
        				shelter.shelterList.remove(i);
        				removed = 1;
        			}
        		}
        		if(removed != 1) {
        			JOptionPane.showMessageDialog(null, "Item not in List!");
        		}
        		sortViewList();
        	}
        	
        });
		shelterView.adoptButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int index = shelterView.list.getSelectedIndex();
        		if(shelter.getShelterList().get(index).getAdopted() == false) {
        			shelter.getShelterList().get(index).setAdopted(true);
        			shelterView.adoptLabel.setText("Not Available");
					shelterView.adoptLabel.setForeground(Color.RED);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Already Adopted!");
        		}
        	}
        });
		shelterView.addButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int addVal = 0;
        		for(int i = 0; i < shelter.shelterList.size(); i++) {
        			if(shelter.shelterList.get(i).getName().compareTo(shelterView.name.getText()) == 0 
        					&& shelter.shelterList.get(i).getType().compareTo(shelterView.type.getText()) == 0 
        					&& shelter.shelterList.get(i).getSpecies().compareTo(shelterView.species.getText()) == 0 
        					&& shelter.shelterList.get(i).getAge() == Integer.parseInt(shelterView.age.getText())) {
        				JOptionPane.showMessageDialog(null, "Already in the shelter!");
        				addVal = 1;
        			}
        		}
        		try {
	        		if(addVal == 0) {
	        			if(shelterView.name.getText().matches("[a-zA-Z]+")) {
		        			BasicAnimal temp = new BasicAnimal();
		        			Pet tempPet = Collections.max(shelter.shelterList, new Id());
		    				temp.setId(tempPet.getId() + 1);
		    				String capName = shelterView.name.getText().substring(0,1).toUpperCase() + shelterView.name.getText().substring(1);
		        			temp.setName(capName);
		        			temp.setType(shelterView.type.getText());
		        			temp.setSpecies(shelterView.species.getText());
		        			temp.setAge(Integer.parseInt(shelterView.age.getText()));
		        			temp.setAdopted(false);
		        			shelter.addPet(temp);
		        			sortViewList();
	        			}
	        			else {
	        				JOptionPane.showMessageDialog(null, "Error: Please Ensure Fields Contain Only Alphabetical Characters And No Spaces");
	        			}
	        		}
        		}catch(Exception f) {
        			JOptionPane.showMessageDialog(null, "Error: Please Ensure Fields Contain Only Alphabetical Characters And No Spaces");
        			f.printStackTrace();
        		}
        	}
        });
	}
	/**
	 * Update list in the View, called by sort buttons after a sort
	 */
	public void sortViewList() {
		shelterView.updateList(shelter.shelterList);
	}
}
