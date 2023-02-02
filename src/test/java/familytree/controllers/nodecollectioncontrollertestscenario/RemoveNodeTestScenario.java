package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveNodeTestScenario extends GenericTestScenario {
  private String id;

  @Builder
  public RemoveNodeTestScenario(
      String errMessage,
      String testCaseName,
      String id) {

    super(errMessage, testCaseName);
    this.id = id;
  }

}
