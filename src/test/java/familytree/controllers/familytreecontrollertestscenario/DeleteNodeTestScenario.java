package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteNodeTestScenario extends GenericTestScenario {
  private String id;

  @Builder
  public DeleteNodeTestScenario(
      String errMessage,
      String testCaseName,
      String id) {

    super(errMessage, testCaseName);
    this.id = id;
  }

}
