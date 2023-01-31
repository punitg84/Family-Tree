package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddDependencyTestScenario extends GenericTestScenario {

  Node parent, child;
  String parentId, childId;
  boolean output;

  @Builder
  public AddDependencyTestScenario(
      String errMessage,
      String testCaseName,
      Node parent,
      Node child,
      String parentId,
      String childId,
      boolean output) {
    
    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
    this.parentId = parentId;
    this.childId = childId;
    this.output = output;
  }

}
