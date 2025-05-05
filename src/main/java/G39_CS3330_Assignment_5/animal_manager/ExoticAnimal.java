package G39_CS3330_Assignment_5.animal_manager;
/**
 * Base class of ExoticAnimal third party type, facilitates reading from the exotic_animals.json input file
 */
class ExoticAnimal {
	String uniqueId;
	String animalName;
	String category;
	String subSpecies;
	int yearsOld;
	
	public ExoticAnimal() {}
	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubSpecies() {
		return subSpecies;
	}

	public void setSubSpecies(String subSpecies) {
		this.subSpecies = subSpecies;
	}

	public int getYearsOld() {
		return yearsOld;
	}

	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}
}
