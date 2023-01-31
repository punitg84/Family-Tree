package familytree.validations;

import static familytree.validations.ValidateNode.validateNode;
import static org.junit.jupiter.api.Assertions.*;

import familytree.models.Node;
import familytree.validations.validatenodetestscenario.ValidateNodeTestScenario;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidateNodeTest {

  private static Stream<ValidateNodeTestScenario> generateTestCaseForValidateNode() {

    Node validNode = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .additionalInfo(new HashMap<>())
        .build();

    Node invalidNode = Node.builder()
        .name("")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .additionalInfo(new HashMap<>())
        .build();

    //Test Case 1 - Valid Node
    ValidateNodeTestScenario testCase1 = ValidateNodeTestScenario.builder()
        .node(validNode)
        .errMessage("")
        .testCaseName("Valid node")
        .build();

    //Test Case 2 - Invalid Node
    ValidateNodeTestScenario testCase2 = ValidateNodeTestScenario.builder()
        .node(invalidNode)
        .errMessage("Name cant be empty or null")
        .testCaseName("Invalid node - name empty")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForValidateNode")
  void testValidateNode(ValidateNodeTestScenario testCase) {

    String testCaseName = testCase.getTestCaseName();
    String expectedErrMessage = testCase.getErrMessage();
    Node node = testCase.getNode();

    try {
      validateNode(node);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }
}