package familytree.controllers.nodecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetChildrenTestScenario extends GenericTestScenario {
  Node node;
  int output;

  @Builder
  public GetChildrenTestScenario(String errMessage, String testCaseName, Node node, int output) {
    super(errMessage, testCaseName);
    this.node = node;
    this.output = output;
  }

}
