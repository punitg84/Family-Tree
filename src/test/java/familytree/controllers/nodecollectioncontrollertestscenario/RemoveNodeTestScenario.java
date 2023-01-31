package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveNodeTestScenario extends GenericTestScenario {

  private List<Node> nodes;
  private String id;
  private int output;

  @Builder
  public RemoveNodeTestScenario(
      String errMessage,
      String testCaseName, List<Node> nodes,
      String id,
      int output) {

    super(errMessage, testCaseName);
    this.nodes = nodes;
    this.id = id;
    this.output = output;
  }

}
