package familytree.controllers.familytreecontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteNodeTestScenario extends GenericTestScenario {

  private Node node;
  private String id;
  private int nodeCollectionSize;

  @Builder
  public DeleteNodeTestScenario(
      String errMessage,
      String testCaseName,
      Node node,
      String id,
      int nodeCollectionSize) {

    super(errMessage, testCaseName);
    this.node = node;
    this.id = id;
    this.nodeCollectionSize = nodeCollectionSize;
  }

}
