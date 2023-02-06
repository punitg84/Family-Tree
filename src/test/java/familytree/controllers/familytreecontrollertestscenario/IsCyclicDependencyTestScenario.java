package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IsCyclicDependencyTestScenario extends GenericTestScenario {
  private Node parent;
  private Node child;
  private boolean output;

  @Builder
  public IsCyclicDependencyTestScenario(
      String errMessage,
      String testCaseName,
      Node parent,
      Node child,
      boolean output) {

    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
    this.output = output;
  }

}
