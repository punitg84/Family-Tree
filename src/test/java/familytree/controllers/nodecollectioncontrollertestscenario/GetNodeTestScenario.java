package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetNodeTestScenario extends GenericTestScenario {
  private List<Node> nodes;
  private String id;

  @Builder
  public GetNodeTestScenario(String errMessage, String testCaseName, List<Node> nodes, String id) {
    super(errMessage, testCaseName);
    this.nodes = nodes;
    this.id = id;
  }

}
