package familytree.validations;

import static familytree.validations.ValidateNodeCollection.validateNodeIdAbsent;
import static familytree.validations.ValidateNodeCollection.validateNodeIdPresent;
import static org.junit.jupiter.api.Assertions.assertEquals;

import familytree.controllers.FamilyTreeController;
import familytree.models.Node;
import familytree.validations.validatenodecollectiontestscenario.ValidateNodeIdTestScenario;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidateNodeCollectionTest {

  private static Stream<ValidateNodeIdTestScenario> generateTestCaseForValidateNodeIdPresent() {

    Node node = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    //Test Case 1 ID exist
    ValidateNodeIdTestScenario testCase1 = ValidateNodeIdTestScenario.builder()
        .nodeList(Arrays.asList(node))
        .id("101")
        .testCaseName("ID exist")
        .errMessage("")
        .build();

    //Test Case 2 ID doesn't exist
    ValidateNodeIdTestScenario testCase2 = ValidateNodeIdTestScenario.builder()
        .nodeList(Arrays.asList(node))
        .id("102")
        .testCaseName("ID doesn't exist")
        .errMessage("ID is not present in the collection")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForValidateNodeIdPresent")
  void testValidateNodeIdPresent(ValidateNodeIdTestScenario testCase) {

    String id = testCase.getId();
    List<Node> nodeList = testCase.getNodeList();
    String testCaseName = testCase.getTestCaseName();
    String expectedErrMessage = testCase.getErrMessage();

    nodeList.stream().forEach(e -> FamilyTreeController.addNode(e.getId(), e.getName()));

    try {
      validateNodeIdPresent(id);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<ValidateNodeIdTestScenario> generateTestCaseForValidateNodeIdAbsent() {

    Node node = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    //Test Case 1 ID exist
    ValidateNodeIdTestScenario testCase1 = ValidateNodeIdTestScenario.builder()
        .nodeList(Arrays.asList(node))
        .id("101")
        .testCaseName("ID exist")
        .errMessage("ID is present in the collection")
        .build();

    //Test Case 2 ID doesn't exist
    ValidateNodeIdTestScenario testCase2 = ValidateNodeIdTestScenario.builder()
        .nodeList(Arrays.asList(node))
        .id("102")
        .testCaseName("ID doesn't exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForValidateNodeIdAbsent")
  void testValidateNodeIdAbsent(ValidateNodeIdTestScenario testCase) {

    String id = testCase.getId();
    List<Node> nodeList = testCase.getNodeList();
    String testCaseName = testCase.getTestCaseName();
    String expectedErrMessage = testCase.getErrMessage();

    nodeList.stream().forEach(e -> FamilyTreeController.addNode(e.getId(), e.getName()));

    try {
      validateNodeIdAbsent(id);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

}