package familytree.controllers.nodecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Getter;

@Getter
public class AddChildTestScenario extends GenericTestScenario {

  Node parent, child;

  public AddChildTestScenario(String errMessage, String testCaseName, Node parent, Node child) {
    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
  }

}
