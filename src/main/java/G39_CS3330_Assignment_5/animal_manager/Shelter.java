package G39_CS3330_Assignment_5.animal_manager;

import java.util.*;
/**
 * Class that represents the animals within the animal shelter
 * is the Model of the MVC format
 * @param <T> abstract object that is an extension of Pet (BasicAnimal, AnimalAdapter)
 */
class Shelter<T extends Pet> {
	List<T> shelterList;
	
	public Shelter() {
		this.shelterList = new ArrayList<>();
	}
	
	public List<T> getShelterList(){
		return shelterList;
	}
	
	public void addPet(T newPet) {
		shelterList.add(newPet);
	}
	/**
	 * basic sort without Comparator specification sorts using Comparable
	 */
	public void sortShelterByName() {
		Collections.sort(shelterList);
	}
	/**
	 * specified Age() Comparator sorts by Age
	 */
	public void sortShelterByAge() {
		Collections.sort(shelterList, new Age());
	}
	/**
	 * specified Species() Comparator sorts by Species
	 */
	public void sortShelterBySpecies() {
		Collections.sort(shelterList, new Species());
	}
	
}



