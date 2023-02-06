package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetNodeTestScenario extends GenericTestScenario {

  private Node node;
  private String id;

  @Builder
  public GetNodeTestScenario(String errMessage, String testCaseName, Node node, String id) {
    super(errMessage, testCaseName);
    this.node = node;
    this.id = id;
  }

}
