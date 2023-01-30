package familytree.validations.validatenodecollectiontestscenario;

import familytree.models.Node;
import familytree.testscenario.GenericTestScenario;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidateNodeIdTestScenario extends GenericTestScenario {
  private List<Node> nodeList;
  private String id;

  @Builder
  public ValidateNodeIdTestScenario(String errMessage,
                                    String testCaseName,
                                    List<Node> nodeList,
                                    String id) {

    super(errMessage, testCaseName);
    this.nodeList = nodeList;
    this.id = id;
  }

}
