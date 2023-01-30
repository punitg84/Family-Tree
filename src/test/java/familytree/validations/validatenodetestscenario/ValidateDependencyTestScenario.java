package familytree.validations.validatenodetestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidateDependencyTestScenario extends GenericTestScenario {
  private Node parent,child;

  @Builder
  public ValidateDependencyTestScenario(String errMessage,
                                        String testCaseName,
                                        Node parent,
                                        Node child) {

    super(errMessage, testCaseName);
    this.parent = parent;
    this.child = child;
  }

}
