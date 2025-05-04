package G39_CS3330_Assignment_5.animal_manager;

class AnimalAdapter extends Pet {
	private ExoticAnimal exoticAnimal;
	
	public AnimalAdapter(ExoticAnimal exoticAnimal) {
		this.exoticAnimal = exoticAnimal;
	}
	
	@Override
	public String getName() {
		return exoticAnimal.getAnimalName();
	}
	
	@Override
	public String getType() {
		return exoticAnimal.getCategory();
	}
	
	@Override
	public String getSpecies() {
		return exoticAnimal.getSubSpecies();
	}
	
	@Override
	public int getAge() {
		return exoticAnimal.getYearsOld();
	}
};
