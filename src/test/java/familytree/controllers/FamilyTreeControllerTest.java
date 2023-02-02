package familytree.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import familytree.controllers.familytreecontrollertestscenario.AddDependencyTestScenario;
import familytree.controllers.familytreecontrollertestscenario.AddNodeTestScenario;
import familytree.controllers.familytreecontrollertestscenario.DeleteDependencyTestScenario;
import familytree.controllers.familytreecontrollertestscenario.DeleteNodeTestScenario;
import familytree.controllers.familytreecontrollertestscenario.GetAncestorsTestScenario;
import familytree.controllers.familytreecontrollertestscenario.GetChildrenTestScenario;
import familytree.controllers.familytreecontrollertestscenario.GetDescendantsTestScenario;
import familytree.controllers.familytreecontrollertestscenario.GetParentTestScenario;
import familytree.controllers.familytreecontrollertestscenario.IsCyclicDependencyTestScenario;
import familytree.models.Node;
import familytree.models.NodeCollection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FamilyTreeControllerTest {

  private static FamilyTreeController familyTreeController;
  private static Node parent, childNode1, childNode2;
  private static NodeCollection nodeCollection;

  @BeforeAll
  static void init() {
    familyTreeController =
        new FamilyTreeController(new NodeCollectionRepo(NodeCollection.getInstance()),
            new NodeController());

    parent = Node.builder()
        .id("3")
        .name("parent")
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    childNode1 = Node.builder()
        .id("1")
        .name("child")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .children(new HashSet<>())
        .build();

    childNode2 = Node.builder()
        .id("2")
        .name("child")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .children(new HashSet<>())
        .build();

    parent.setChildren(new HashSet<>(Arrays.asList(childNode1, childNode2)));

    nodeCollection = NodeCollection.getInstance();
  }

  @BeforeEach
  void setup() {
    nodeCollection.getNodeMap().put(parent.getId(), parent);
    nodeCollection.getNodeMap().put(childNode1.getId(), childNode1);
    nodeCollection.getNodeMap().put(childNode2.getId(), childNode2);
  }

  private static Stream<GetParentTestScenario> generateTestCaseForGetParent() {
    //Test Case 1 Node having parent existing
    GetParentTestScenario testCase1 = GetParentTestScenario.builder()
        .nodeId("1")
        .output(1)
        .testCaseName("Only one parent exist")
        .errMessage("")
        .build();

    //Test Case 2 Node having no parent existing
    GetParentTestScenario testCase2 = GetParentTestScenario.builder()
        .nodeId("3")
        .output(0)
        .testCaseName("No parent exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetParent")
  void testGetParent(GetParentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String nodeId = testCase.getNodeId();
    String errMessage = testCase.getErrMessage();
    int expectedOutput = testCase.getOutput();
    try {
      Set<Node> parents = familyTreeController.getParent(nodeId);
      assertEquals(expectedOutput, parents.size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<GetChildrenTestScenario> generateTestCaseForGetChildren() {
    //Test Case 1 Node having no children
    GetChildrenTestScenario testCase1 = GetChildrenTestScenario.builder()
        .nodeId("1")
        .output(0)
        .testCaseName("no children exist")
        .errMessage("")
        .build();

    //Test Case 2 Node having multiple children
    GetChildrenTestScenario testCase2 = GetChildrenTestScenario.builder()
        .nodeId("3")
        .output(2)
        .testCaseName("2 children exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetChildren")
  void testGetChildren(GetChildrenTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String nodeId = testCase.getNodeId();
    String errMessage = testCase.getErrMessage();
    int expectedOutput = testCase.getOutput();
    try {
      Set<Node> children = familyTreeController.getChildren(nodeId);
      assertEquals(expectedOutput, children.size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<GetAncestorsTestScenario> generateTestCaseForGetAncestors() {
    //Test Case 1 Node having one ancestor
    GetAncestorsTestScenario testCase1 = GetAncestorsTestScenario.builder()
        .nodeId("1")
        .output(1)
        .testCaseName("no children exist")
        .errMessage("")
        .build();

    //Test Case 2 Node having no ancestor
    GetAncestorsTestScenario testCase2 = GetAncestorsTestScenario.builder()
        .nodeId("3")
        .output(0)
        .testCaseName("2 children exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetAncestors")
  void testGetAncestors(GetAncestorsTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String nodeId = testCase.getNodeId();
    String errMessage = testCase.getErrMessage();
    int expectedOutput = testCase.getOutput();
    try {
      Set<Node> ancestors = familyTreeController.getAncestors(nodeId);
      assertEquals(expectedOutput, ancestors.size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<GetDescendantsTestScenario> generateTestCaseForGetDescendants() {
    //Test Case 1 Node having no descendants
    GetDescendantsTestScenario testCase1 = GetDescendantsTestScenario.builder()
        .nodeId("1")
        .output(0)
        .testCaseName("no descendants exist")
        .errMessage("")
        .build();

    //Test Case 2 Node having 2 descendants
    GetDescendantsTestScenario testCase2 = GetDescendantsTestScenario.builder()
        .nodeId("3")
        .output(2)
        .testCaseName("2 descendants exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetDescendants")
  void testGetDescendants(GetDescendantsTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String nodeId = testCase.getNodeId();
    String errMessage = testCase.getErrMessage();
    int expectedOutput = testCase.getOutput();
    try {
      Set<Node> descendants = familyTreeController.getDescendants(nodeId);
      assertEquals(expectedOutput, descendants.size(), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<DeleteDependencyTestScenario> generateTestCaseForDeleteDependency() {
    //Test Case 1 dependency Exist
    DeleteDependencyTestScenario testCase1 = DeleteDependencyTestScenario.builder()
        .parentId("3")
        .childId("1")
        .testCaseName("dependency exist")
        .errMessage("")
        .build();

    //Test Case 2 no dependency exist
    DeleteDependencyTestScenario testCase2 = DeleteDependencyTestScenario.builder()
        .parentId("1")
        .childId("3")
        .testCaseName("no dependency exist")
        .errMessage("no dependency exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForDeleteDependency")
  void testDeleteDependency(DeleteDependencyTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String parentId = testCase.getParentId();
    String childId = testCase.getChildId();
    String expectedErrMessage = testCase.getErrMessage();
    try {
      familyTreeController.deleteDependency(parentId, childId);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<DeleteNodeTestScenario> generateTestCaseForDeleteNode() {
    //Test Case 1 Id doesnt exist
    DeleteNodeTestScenario testCase1 = DeleteNodeTestScenario.builder()
        .id("4")
        .testCaseName("node doesn't exist")
        .errMessage("node doesn't exist")
        .build();

    //Test Case 2 Id exist
    DeleteNodeTestScenario testCase2 = DeleteNodeTestScenario.builder()
        .id("1")
        .testCaseName("node exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForDeleteNode")
  void testDeleteNode(DeleteNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String id = testCase.getId();
    String expectedErrMessage = testCase.getErrMessage();
    try {
      familyTreeController.deleteNode(id);
      assertEquals(false, nodeCollection.getNodeMap().containsKey(id), testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<AddNodeTestScenario> generateTestCaseForAddNode() {
    //Test Case 1 Valid info
    AddNodeTestScenario testCase1 = AddNodeTestScenario.builder()
        .id("4")
        .name("grand parent")
        .additionalInfo(new HashMap<>() {{
          put("Gender", "Male");
        }})
        .testCaseName("Valid info")
        .errMessage("")
        .build();

    //Test Case 2 Invalid info
    AddNodeTestScenario testCase2 = AddNodeTestScenario.builder()
        .id("")
        .name("grand parent")
        .additionalInfo(new HashMap<>() {{
          put("Gender", "Male");
        }})
        .testCaseName("Invalid info")
        .errMessage("Invalid info")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForAddNode")
  void testAddNode(AddNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String expectedErrMessage = testCase.getErrMessage();
    String name = testCase.getName();
    String id = testCase.getId();
    HashMap<String, String> additionalInfo = testCase.getAdditionalInfo();
    try {
      familyTreeController.addNode(id, name, additionalInfo);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<AddDependencyTestScenario> generateTestCaseForAddDependency() {
    //Test Case 1 dependency Exist
    AddDependencyTestScenario testCase1 = AddDependencyTestScenario.builder()
        .parentId("3")
        .childId("1")
        .testCaseName("dependency exist")
        .errMessage("dependency exist")
        .build();

    //Test Case 2 no dependency exist
    AddDependencyTestScenario testCase2 = AddDependencyTestScenario.builder()
        .parentId("1")
        .childId("2")
        .testCaseName("no dependency exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForAddDependency")
  void addDependency(AddDependencyTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String parentId = testCase.getParentId();
    String childId = testCase.getChildId();
    String expectedErrMessage = testCase.getErrMessage();
    try {
      familyTreeController.addDependency(parentId, childId);
      assertEquals(expectedErrMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<IsCyclicDependencyTestScenario> generateTestCaseForIsCyclicDependency() {
    //Test Case 1 dependency Exist
    IsCyclicDependencyTestScenario testCase1 = IsCyclicDependencyTestScenario.builder()
        .parent(parent)
        .child(childNode1)
        .output(true)
        .testCaseName("cyclic dependency exist")
        .errMessage("cyclic dependency exist")
        .build();

    //Test Case 2 no dependency exist
    IsCyclicDependencyTestScenario testCase2 = IsCyclicDependencyTestScenario.builder()
        .parent(childNode2)
        .child(childNode1)
        .output(false)
        .testCaseName("no cyclic dependency exist")
        .errMessage("")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForIsCyclicDependency")
  void isCyclicDependency(IsCyclicDependencyTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    Node parent = testCase.getParent();
    Node child = testCase.getChild();
    boolean output = testCase.isOutput();
    String expectedErrMessage = testCase.getErrMessage();
    try {
      boolean actualOutput = familyTreeController.isCyclicDependency(parent, child);
      assertEquals(output, actualOutput, testCaseName);
    } catch (Exception e) {
      assertEquals(expectedErrMessage, e.getMessage(), testCaseName);
    }
  }

  @AfterEach
  void cleanUp() {
    nodeCollection.clearMapping();
  }

}