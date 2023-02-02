package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDescendantsTestScenario extends GenericTestScenario {
  private String nodeId;
  private int output;

  @Builder
  public GetDescendantsTestScenario(
      String errMessage,
      String testCaseName,
      String nodeId,
      int output) {

    super(errMessage, testCaseName);
    this.nodeId = nodeId;
    this.output = output;
  }
}
