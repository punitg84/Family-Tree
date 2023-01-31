package familytree.controllers.nodecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveParentTestScenario extends GenericTestScenario {

  Node parent,child;
  int output;

  @Builder
  public RemoveParentTestScenario(
      String errMessage,
      String testCaseName,
      Node parent,
      Node child,
      int output) {

    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
    this.output = output;
  }

}
