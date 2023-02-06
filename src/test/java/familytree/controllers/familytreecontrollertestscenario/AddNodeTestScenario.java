package familytree.controllers.familytreecontrollertestscenario;

import familytree.testscenario.GenericTestScenario;
import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddNodeTestScenario extends GenericTestScenario {

  private String name, id;
  private HashMap<String, String> additionalInfo;
  private int nodeCollectionSize;

  @Builder
  public AddNodeTestScenario(
      String errMessage,
      String testCaseName,
      String name,
      String id,
      HashMap<String, String> additionalInfo,
      int nodeCollectionSize) {
    super(errMessage, testCaseName);
    this.name = name;
    this.id = id;
    this.additionalInfo = additionalInfo;
    this.nodeCollectionSize = nodeCollectionSize;
  }

}
