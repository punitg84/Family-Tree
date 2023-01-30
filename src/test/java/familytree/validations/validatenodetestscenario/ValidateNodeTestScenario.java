package familytree.validations.validatenodetestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidateNodeTestScenario extends GenericTestScenario {
  Node node;

  @Builder
  public ValidateNodeTestScenario(String errMessage, String testCaseName, Node node) {

    super(errMessage, testCaseName);
    this.node = node;
  }

}
