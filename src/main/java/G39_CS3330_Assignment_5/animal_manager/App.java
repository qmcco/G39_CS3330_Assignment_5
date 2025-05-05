package G39_CS3330_Assignment_5.animal_manager;
/**
 * Main App class initializes the animal shelter adoption app
 */
public class App {
  public static void main(String[] args) {
    System.out.println("Hello Worlddd!");
    ShelterView view = new ShelterView();
    Shelter shelter = new Shelter();
    ShelterController controllerTest = new ShelterController(shelter, view);
    controllerTest.initShelter();
    controllerTest.initView();
  }
}
