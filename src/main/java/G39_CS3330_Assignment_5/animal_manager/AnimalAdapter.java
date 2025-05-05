package G39_CS3330_Assignment_5.animal_manager;
/**
 * Adapter class to facilitate use of the third party ExoticAnimal 
 * class within the abstract Pet class based animal shelter adoption system
 * 
 */
class AnimalAdapter extends Pet {
	/**
	 * name, type, species, and age fields can be extracted from the ExoticAnimal object
	 * id and adopted status fields have to be set separately 
	 */
	private ExoticAnimal exoticAnimal;
	Boolean adopted = false;
	int id = 0;
	
	public AnimalAdapter(ExoticAnimal exoticAnimal) {
		this.exoticAnimal = exoticAnimal;
	}
	
	@Override
	public String getName() {
		return exoticAnimal.getAnimalName();
	}
	
	@Override
	public void setName(String name) {
		exoticAnimal.setAnimalName(name);
	}
	
	@Override
	public String getType() {
		return exoticAnimal.getCategory();
	}
	
	@Override
	public void setType(String type) {
		exoticAnimal.setCategory(type);
	}
	
	@Override
	public String getSpecies() {
		return exoticAnimal.getSubSpecies();
	}
	
	@Override
	public void setSpecies(String species) {
		exoticAnimal.setSubSpecies(species);
	}
	
	@Override
	public int getAge() {
		return exoticAnimal.getYearsOld();
	}
	
	@Override
	public void setAge(int age) {
		exoticAnimal.setYearsOld(age);
	}
	
	@Override
	public Boolean getAdopted() {
		return adopted;
	}
	
	@Override
	public void setAdopted(Boolean adopted) {
		this.adopted = adopted;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
};
