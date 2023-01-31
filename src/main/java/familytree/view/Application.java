package familytree.view;

import familytree.controllers.FamilyTreeController;
import familytree.controllers.NodeCollectionRepo;
import familytree.controllers.NodeController;
import familytree.models.NodeCollection;
import java.util.Scanner;

public class Application {

  private Scanner scanner = new Scanner(System.in);
  private boolean isUserExited;
  private FamilyTreeController familyTreeController;

  public Application() {
    familyTreeController = new FamilyTreeController(
        new NodeCollectionRepo(NodeCollection.getInstance())
        , new NodeController());
  }

  private static int inputInteger() throws Exception {
    return 0;
  }

  private void showErrors(final Exception exception) {
    System.out.printf("The following error occurred :%s", exception.getMessage());
  }

  private void exitMenu() {
    //set isUserExited
  }

  private void addNode() {
    //call addNode(id,name) in familyTreeController
  }

  private void addDependency() {
    // call addDependency(id1,id2) in familyTreeController
  }

  private void deleteNode() {
    //call deleteNode(id) in familyTreeController
  }

  private void deleteDependency() {
    //call deleteDependency(id1,id2) in familyTreeController
  }

  private void getDescendants() {
    //call getDescendants(id) - in familyTreeController
  }

  private void getAncestors() {
    //call getAncestors(id) - in familyTreeController
  }

  private void getChildren() {
    //call getChildren(id) - in familyTreeController
  }

  private void getParent() {
    //call getParent(id) - in familyTreeController
  }

  private void selectOptionFromMenu() {
    //try catch for error handling
    //call each function respectively

  }

  private void showMenu() {

    System.out.println("Select one of the 9 option available by entering a number\n" +
        "1. Get the immediate parents of a node\n" +
        "2. Get the immediate children of a node\n" +
        "3. Get the ancestors of a node\n" +
        "4. Get the descendants of a node\n" +
        "5. Delete dependency from a tree\n" +
        "6. Delete a node from a tree\n" +
        "7. Add a new dependency to a tree\n" +
        "8. Add a new node to tree\n" +
        "9. Exit");

    //call select option
  }

  public void run() {
    do {
      showMenu();
    } while (!isUserExited);
  }

}
