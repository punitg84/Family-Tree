package familytree.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import familytree.controllers.nodecontrollertestscenario.AddChildTestScenario;
import familytree.controllers.nodecontrollertestscenario.AddParentTestScenario;
import familytree.controllers.nodecontrollertestscenario.CreateNodeTestScenario;
import familytree.controllers.nodecontrollertestscenario.GetChildrenTestScenario;
import familytree.controllers.nodecontrollertestscenario.GetParentTestScenario;
import familytree.controllers.nodecontrollertestscenario.IsDependencyPresentTestScenario;
import familytree.controllers.nodecontrollertestscenario.RemoveChildTestScenario;
import familytree.controllers.nodecontrollertestscenario.RemoveParentTestScenario;
import familytree.models.Node;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class NodeControllerTest {

  private static NodeController nodeController;
  private static Node parent, childNode1, childNode2, grandParent;

  @BeforeEach
  void setup() {
    nodeController = new NodeController();

    grandParent = Node.builder()
        .id("4")
        .name("grand parent")
        .build();

    parent = Node.builder()
        .id("3")
        .name("parent")
        .build();

    childNode1 = Node.builder()
        .id("1")
        .name("child")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .build();

    childNode2 = Node.builder()
        .id("2")
        .name("child")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .build();

    parent.setChildren(new HashSet<>(Arrays.asList(childNode1, childNode2)));
  }

  private static Stream<GetParentTestScenario> generateTestCaseForGetParent() {
    //Test Case Node having 0 parent
    GetParentTestScenario testCase1 = GetParentTestScenario.builder()
        .node(parent)
        .output(0)
        .testCaseName("No parents")
        .errMessage("")
        .build();

    //Test case node having 1 parent
    GetParentTestScenario testCase2 = GetParentTestScenario.builder()
        .node(childNode1)
        .output(1)
        .testCaseName("1 parent")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetParent")
  void testGetParent(GetParentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node node = testCase.getNode();
    int output = testCase.getOutput();
    try {
      assertEquals(output, nodeController.getParent(node).size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<GetChildrenTestScenario> generateTestCaseForGetChildren() {
    //Test Case Node having 2 children
    GetChildrenTestScenario testCase1 = GetChildrenTestScenario.builder()
        .node(parent)
        .output(2)
        .testCaseName("2 children")
        .errMessage("")
        .build();

    //Test case node having 0 children
    GetChildrenTestScenario testCase2 = GetChildrenTestScenario.builder()
        .node(childNode1)
        .output(0)
        .testCaseName("0 children")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetChildren")
  void getChildren(GetChildrenTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node node = testCase.getNode();
    int output = testCase.getOutput();
    try {
      assertEquals(output, nodeController.getChildren(node).size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<CreateNodeTestScenario> generateTestCaseForCreateNode() {
    //Test Case invalid node
    CreateNodeTestScenario testCase1 = CreateNodeTestScenario.builder()
        .id("")
        .name("grand parent")
        .additionalInfo(new HashMap<>() {{
          put("Gender", "Male");
        }})
        .testCaseName("Invalid info")
        .errMessage("Invalid info")
        .build();

    //Test case valid node
    CreateNodeTestScenario testCase2 = CreateNodeTestScenario.builder()
        .id("4")
        .name("grand parent")
        .additionalInfo(new HashMap<>() {{
          put("Gender", "Male");
        }})
        .testCaseName("Valid info")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForCreateNode")
  void createNode(CreateNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String expectedErrMessage = testCase.getErrMessage();
    String name = testCase.getName();
    String id = testCase.getId();
    HashMap<String, String> additionalInfo = testCase.getAdditionalInfo();
    try {
      nodeController.createNode(id, name, additionalInfo);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<AddChildTestScenario> generateTestCaseForAddChild() {
    //Test Case Valid
    AddChildTestScenario testCase1 = AddChildTestScenario.builder()
        .parent(grandParent)
        .child(parent)
        .output(1)
        .testCaseName("Valid addition")
        .errMessage("")
        .build();

    //Test case Invalid
    AddChildTestScenario testCase2 = AddChildTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(2)
        .testCaseName("Invalid addition")
        .errMessage("Already dependency exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForAddChild")
  void addChild(AddChildTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    int output = testCase.getOutput();
    try {
      nodeController.addChild(parent, child);
      assertEquals(output, parent.getChildren().size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<RemoveChildTestScenario> generateTestCaseForRemoveChild() {
    //Test Case Valid
    RemoveChildTestScenario testCase1 = RemoveChildTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(1)
        .testCaseName("Valid removal")
        .errMessage("")
        .build();

    //Test case Invalid
    RemoveChildTestScenario testCase2 = RemoveChildTestScenario.builder()
        .parent(grandParent)
        .child(childNode1)
        .output(2)
        .testCaseName("Invalid removal")
        .errMessage("dependency doesn't exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForRemoveChild")
  void removeChild(RemoveChildTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    int output = testCase.getOutput();
    try {
      nodeController.removeChild(parent, child);
      assertEquals(output, parent.getChildren().size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<AddParentTestScenario> generateTestCaseForAddParent() {
    //Test Case Valid
    AddParentTestScenario testCase1 = AddParentTestScenario.builder()
        .parent(grandParent)
        .child(parent)
        .output(1)
        .testCaseName("Valid addition")
        .errMessage("")
        .build();

    //Test case Invalid
    AddParentTestScenario testCase2 = AddParentTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(1)
        .testCaseName("Invalid addition")
        .errMessage("Already dependency exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForAddParent")
  void addParent(AddParentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    int output = testCase.getOutput();
    try {
      nodeController.addParent(parent, child);
      assertEquals(output, child.getParent().size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<RemoveParentTestScenario> generateTestCaseForRemoveParent() {
    //Test Case Invalid
    RemoveParentTestScenario testCase1 = RemoveParentTestScenario.builder()
        .parent(grandParent)
        .child(parent)
        .output(0)
        .testCaseName("Invalid Removal")
        .errMessage("Dependency doesn't exist")
        .build();

    //Test case Invalid
    RemoveParentTestScenario testCase2 = RemoveParentTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(0)
        .testCaseName("Valid Removal")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForRemoveParent")
  void removeParent(RemoveParentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    int output = testCase.getOutput();
    try {
      nodeController.removeParent(parent, child);
      assertEquals(output, child.getParent().size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<IsDependencyPresentTestScenario> generateTestCaseForIsDependencyPresent() {
    //Test Case Invalid
    IsDependencyPresentTestScenario testCase1 = IsDependencyPresentTestScenario.builder()
        .parent(grandParent)
        .child(parent)
        .output(false)
        .testCaseName("Invalid dependency")
        .errMessage("Dependency doesn't exist")
        .build();

    //Test case Invalid
    IsDependencyPresentTestScenario testCase2 = IsDependencyPresentTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(true)
        .testCaseName("Valid dependency")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForIsDependencyPresent")
  void isDependencyPresent(IsDependencyPresentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    boolean output = testCase.isOutput();
    try {
      assertEquals(output, nodeController.isChildPresent(parent, child), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

}