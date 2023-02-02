package familytree.controllers.nodecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IsDependencyPresentTestScenario extends GenericTestScenario {
  Node parent,child;
  boolean output;

  @Builder
  public IsDependencyPresentTestScenario(String errMessage, String testCaseName, Node parent,
                                         Node child, boolean output) {
    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
    this.output = output;
  }

}
