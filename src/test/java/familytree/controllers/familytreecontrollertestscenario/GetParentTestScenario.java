package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetParentTestScenario extends GenericTestScenario {
  private String nodeId;
  private int output;

  @Builder
  public GetParentTestScenario(
      String errMessage,
      String testCaseName,
      String nodeId,
      int output) {

    super(errMessage, testCaseName);
    this.nodeId = nodeId;
    this.output = output;
  }
}
