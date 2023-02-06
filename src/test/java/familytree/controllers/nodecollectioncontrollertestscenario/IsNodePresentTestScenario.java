package familytree.controllers.nodecollectioncontrollertestscenario;

import familytree.testscenario.GenericTestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IsNodePresentTestScenario extends GenericTestScenario {
  private String id;
  private boolean output;

  @Builder
  public IsNodePresentTestScenario(String errMessage, String testCaseName, String id,
                                   boolean output) {
    super(errMessage, testCaseName);
    this.id = id;
    this.output = output;
  }

}
