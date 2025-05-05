package G39_CS3330_Assignment_5.animal_manager;

public class App {
  public static void main(String[] args) {
    System.out.println("Hello Worlddd!");
    ShelterView testView = new ShelterView();
    Shelter testShelter = new Shelter();
    ShelterController controllerTest = new ShelterController(testShelter, testView);
    controllerTest.initShelter();
    controllerTest.initView();
  }
}
