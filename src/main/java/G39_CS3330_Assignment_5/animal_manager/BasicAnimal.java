package G39_CS3330_Assignment_5.animal_manager;

class BasicAnimal extends Pet {
	int id;
	String name;
	String type;
	String species;
	int age;
	Boolean adopted;
	
	public BasicAnimal() {}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public String getSpecies() {
		return species;
	}
	
	@Override
	public int getAge() {
		return age;
	}
	
	public Boolean getAdopted() {
		return adopted;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setSepcies(String species) {
		this.species = species;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAdopted(Boolean adopted) {
		this.adopted = adopted;
	}
}
