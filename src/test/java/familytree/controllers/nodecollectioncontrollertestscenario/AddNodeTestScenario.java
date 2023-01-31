package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddNodeTestScenario extends GenericTestScenario {

  private Node node;
  private int output;

  @Builder
  public AddNodeTestScenario(String errMessage, String testCaseName, Node node, int output) {
    super(errMessage, testCaseName);
    this.node = node;
    this.output = output;
  }

}
