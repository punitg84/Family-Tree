package familytree.view;

import familytree.constants.UserChoice;
import familytree.controllers.FamilyTreeController;
import familytree.controllers.NodeCollectionRepo;
import familytree.controllers.NodeController;
import familytree.models.NodeCollection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

  private static Scanner scanner = new Scanner(System.in);
  private boolean isUserExited;
  private FamilyTreeController familyTreeController;

  public Application() {
    familyTreeController = new FamilyTreeController(
        new NodeCollectionRepo(NodeCollection.getInstance()),
        new NodeController());
  }

  private static int inputInteger() throws Exception {
    try {
      return scanner.nextInt();
    } catch (Exception e) {
      scanner.next();
      throw new Exception("Invalid input");
    } finally {
      scanner.nextLine(); //Skip enter
    }
  }

  private void showErrors(final Exception exception) {
    log.error(String.format("The following error occurred :%s", exception.getMessage()));
  }

  private void exitMenu() {
    log.info("The application is terminating");
    isUserExited = true;
  }

  private void addNode() throws Exception {
    log.info("Enter Info in the following order line by line\n"
        + "1. ID\n"
        + "2. Name\n"
        + "3. Number of additional info\n"
        + "4. Space separated key value pairs line by line");

    String id = scanner.nextLine();
    String name = scanner.nextLine();
    int noOfAdditionalInfo = inputInteger();
    Map<String, String> additionalInfo = new HashMap<>();

    for (int i = 1; i <= noOfAdditionalInfo; i++) {
      String input = scanner.nextLine();
      String[] parts = input.split(" ", 2);
      if (parts.length != 2) {
        throw new Exception(String.format("Invalid input %s", input));
      }
      additionalInfo.put(parts[0], parts[1]);
    }

    familyTreeController.addNode(id, name, additionalInfo);

    log.info("Node was successfully created");
  }

  private void addDependency() throws Exception {
    log.info("Enter ID in the following order line by line\n"
        + "1. Parent\n"
        + "2. Child");

    String parentId = scanner.nextLine();
    String childId = scanner.nextLine();

    familyTreeController.addDependency(parentId, childId);

    log.info("Dependency was successfully added");
  }

  private void deleteNode() throws Exception {
    log.info("Enter ID of the node to delete it");

    String id = scanner.nextLine();

    familyTreeController.deleteNode(id);

    log.info("Node was successfully deleted");
  }

  private void deleteDependency() throws Exception {
    log.info("Enter ID in the following order line by line\n"
        + "1. Parent\n"
        + "2.Child");

    String parentId = scanner.nextLine();
    String childId = scanner.nextLine();

    familyTreeController.deleteDependency(parentId, childId);

    log.info("Dependency was successfully removed");
  }

  private void getDescendants() throws Exception {
    log.info("Enter ID of the node to fetch descendants");

    String id = scanner.nextLine();

    log.info(familyTreeController.getDescendants(id));
  }

  private void getAncestors() throws Exception {
    log.info("Enter ID of the node to fetch ancestors");

    String id = scanner.nextLine();

    log.info(familyTreeController.getAncestors(id));
  }

  private void getChildren() throws Exception {
    log.info("Enter ID of the node to fetch children");

    String id = scanner.nextLine();

    log.info(familyTreeController.getChildren(id));
  }

  private void getParent() throws Exception {
    log.info("Enter ID of the node to fetch parents");

    String id = scanner.nextLine();

    log.info(familyTreeController.getParent(id));
  }

  private void selectOptionFromMenu() {
    try {
      int option = inputInteger();

      switch (option) {
        case UserChoice.GET_IMMEDIATE_PARENT_OPTION -> getParent();
        case UserChoice.GET_IMMEDIATE_CHILDREN_OPTION -> getChildren();
        case UserChoice.GET_ANCESTORS_OPTION -> getAncestors();
        case UserChoice.GET_DESCENDANTS_OPTION -> getDescendants();
        case UserChoice.DELETE_DEPENDENCY_OPTION -> deleteDependency();
        case UserChoice.DELETE_NODE_OPTION -> deleteNode();
        case UserChoice.ADD_DEPENDENCY_OPTION -> addDependency();
        case UserChoice.ADD_NODE_OPTION -> addNode();
        case UserChoice.EXIT_OPTION -> exitMenu();
        default -> throw new Exception(String.format("Invalid Option : %s", option));
      }

    } catch (Exception e) {
      showErrors(e);
    }
  }

  private void showMenu() {
    log.info("Select one of the 9 option available by entering a number\n"
        + "1. Get the immediate parents of a node\n"
        + "2. Get the immediate children of a node\n"
        + "3. Get the ancestors of a node\n"
        + "4. Get the descendants of a node\n"
        + "5. Delete dependency from a tree\n"
        + "6. Delete a node from a tree\n"
        + "7. Add a new dependency to a tree\n"
        + "8. Add a new node to tree\n"
        + "9. Exit");

    selectOptionFromMenu();
  }

  public void run() {
    do {
      showMenu();
    } while (!isUserExited);
  }

}
