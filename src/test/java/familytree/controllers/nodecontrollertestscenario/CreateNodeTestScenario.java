package familytree.controllers.nodecontrollertestscenario;

import familytree.testscenario.GenericTestScenario;
import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateNodeTestScenario extends GenericTestScenario {
  private String name, id;
  private HashMap<String, String> additionalInfo;

  @Builder
  public CreateNodeTestScenario(
      String errMessage,
      String testCaseName,
      String name,
      String id,
      HashMap<String, String> additionalInfo) {

    super(errMessage, testCaseName);
    this.name = name;
    this.id = id;
    this.additionalInfo = additionalInfo;
  }
}
