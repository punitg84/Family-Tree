package familytree.validations;

import static familytree.validations.ValidateNode.validateDependencyAbsent;
import static familytree.validations.ValidateNode.validateDependencyPresent;
import static familytree.validations.ValidateNode.validateNode;
import static org.junit.jupiter.api.Assertions.*;

import familytree.models.Node;
import familytree.validations.validatenodecollectiontestscenario.ValidateNodeIdTestScenario;
import familytree.validations.validatenodetestscenario.ValidateDependencyTestScenario;
import familytree.validations.validatenodetestscenario.ValidateNodeTestScenario;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidateNodeTest {

  private static Stream<ValidateNodeTestScenario> generateTestCaseForValidateNode() {

    Node validNode = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    Node invalidNode = Node.builder()
        .name("")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
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

  private static Stream<ValidateDependencyTestScenario> generateTestCaseForValidateDependencyPresent() {

    Node child = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    Node parentWithNoChild = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    Node parentWithGivenChild = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>(Arrays.asList(child)))
        .build();

    //Test Case 1 Dependency doesn't exist
    ValidateDependencyTestScenario testCase1 = ValidateDependencyTestScenario.builder()
        .parent(parentWithNoChild)
        .child(child)
        .testCaseName("Dependency doesn't exist")
        .errMessage("Dependency doesn't exist")
        .build();

    //Test Case 2 Dependency exist
    ValidateDependencyTestScenario testCase2 = ValidateDependencyTestScenario.builder()
        .parent(parentWithGivenChild)
        .child(child)
        .testCaseName("Dependency exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForValidateDependencyPresent")
  void testValidateDependencyPresent(ValidateDependencyTestScenario testCase) {

    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    String expectedErrMessage = testCase.getErrMessage();
    String testCaseName = testCase.getTestCaseName();

    try {
      validateDependencyPresent(parent, child);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<ValidateDependencyTestScenario> generateTestCaseForValidateDependencyAbsent() {


    Node child = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    Node parentWithNoChild = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    Node parentWithGivenChild = Node.builder()
        .name("Node 1")
        .id("101")
        .parent(new HashSet<>())
        .children(new HashSet<>(Arrays.asList(child)))
        .build();

    //Test Case 1 Dependency doesn't exist
    ValidateDependencyTestScenario testCase1 = ValidateDependencyTestScenario.builder()
        .parent(parentWithNoChild)
        .child(child)
        .testCaseName("Dependency doesn't exist")
        .errMessage("")
        .build();

    //Test Case 2 Dependency exist
    ValidateDependencyTestScenario testCase2 = ValidateDependencyTestScenario.builder()
        .parent(parentWithGivenChild)
        .child(child)
        .testCaseName("Dependency exist")
        .errMessage("Dependency exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForValidateDependencyAbsent")
  void testValidateDependencyAbsent(ValidateDependencyTestScenario testCase) {

    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    String expectedErrMessage = testCase.getErrMessage();
    String testCaseName = testCase.getTestCaseName();

    try {
      validateDependencyAbsent(parent, child);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

}