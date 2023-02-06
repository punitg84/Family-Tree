package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteDependencyTestScenario extends GenericTestScenario {
  String parentId, childId;
  boolean output;

  @Builder
  public DeleteDependencyTestScenario(
      String errMessage,
      String testCaseName,
      String parentId,
      String childId,
      boolean output) {

    super(errMessage, testCaseName);
    this.parentId = parentId;
    this.childId = childId;
    this.output = output;
  }

}
