package G39_CS3330_Assignment_5.animal_manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;

class ShelterController {
	private Shelter<Pet> shelter;
	private ShelterView shelterView;
	
	public ShelterController(Shelter<Pet> shelter, ShelterView shelterView) {
		this.shelter = shelter;
		this.shelterView = shelterView;
	}
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
				System.out.println(temp.getId());
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initView() {
		shelterView.initShelterView(shelter.shelterList);
		shelterView.button.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		shelter.sortShelterByName();
        		sortViewList();
        	}
        });
		shelterView.adoptButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int index = shelterView.list.getSelectedIndex();
        		if(shelter.getShelterList().get(index).getAdopted() == false) {
        			shelter.getShelterList().get(index).setAdopted(true);
        		}
        	}
        });
	}
	
	public void sortViewList() {
		shelterView.updateList(shelter.shelterList);
	}
}
