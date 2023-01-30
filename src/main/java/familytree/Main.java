package familytree;

import java.util.Scanner;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

  private Scanner scanner = new Scanner(System.in);
  private static boolean isUserExited;

  private static int inputInteger() throws Exception {
    return 0;
  }

  private static void showErrors(final String errMessage) {
    System.out.printf("The following error occurred :%s", errMessage);
  }

  private static void exitMenu(){
    //set isUserExited
  }

  private static void addNode() {
    //call addNode(id,name) in familyTreeController
  }

  private static void addDependency() {
    // call addDependency(id1,id2) in familyTreeController
  }

  private static void deleteNode() {
    //call deleteNode(id) in familyTreeController
  }

  private static void deleteDependency() {
    //call deleteDependency(id1,id2) in familyTreeController
  }

  private static void getDescendants() {
    //call getDescendants(id) - in familyTreeController
  }

  private static void getAncestors() {
    //call getAncestors(id) - in familyTreeController
  }

  private static void getChildren() {
    //call getChildren(id) - in familyTreeController
  }

  private static void getParent() {
    //call getParent(id) - in familyTreeController
  }

  private static void selectOptionFromMenu() {
    //try catch for error handling
    //call each function respectively

  }

  private static void showMenu() {

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

  public static void main(String[] args) {

    do {
      showMenu();
    } while (!isUserExited);

  }

}
