package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetAncestorsTestScenario extends GenericTestScenario {
  private List<Node> nodes;
  private Node currNode;
  private int output;

  @Builder
  public GetAncestorsTestScenario(
      String errMessage,
      String testCaseName,
      List<Node> nodes,
      Node currNode,
      int output) {

    super(errMessage, testCaseName);
    this.nodes = nodes;
    this.currNode = currNode;
    this.output = output;
  }

}
