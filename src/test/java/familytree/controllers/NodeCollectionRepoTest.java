package familytree.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import familytree.controllers.nodecollectioncontrollertestscenario.AddNodeTestScenario;
import familytree.controllers.nodecollectioncontrollertestscenario.GetNodeTestScenario;
import familytree.controllers.nodecollectioncontrollertestscenario.IsNodePresentTestScenario;
import familytree.controllers.nodecollectioncontrollertestscenario.RemoveNodeTestScenario;
import familytree.models.Node;
import familytree.models.NodeCollection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class NodeCollectionRepoTest {

  private static NodeCollectionRepo nodeCollectionRepo;
  private static Node parent, childNode1, validNode, duplicateIdNode;
  private static NodeCollection nodeCollection;

  @BeforeAll
  static void init() {
    nodeCollectionRepo = new NodeCollectionRepo(NodeCollection.getInstance());

    parent = Node.builder()
        .id("3")
        .name("parent")
        .build();

    childNode1 = Node.builder()
        .id("1")
        .name("child")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .build();

    validNode = Node.builder()
        .id("2")
        .name("valid Node")
        .parent(new HashSet<>(Arrays.asList(parent)))
        .build();

    duplicateIdNode = Node.builder()
        .id("3")
        .name("duplicate parent")
        .build();

    parent.setChildren(new HashSet<>(Arrays.asList(childNode1)));

    nodeCollection = NodeCollection.getInstance();
  }

  @BeforeEach
  void setup() {
    nodeCollection.addMapping(parent.getId(), parent);
    nodeCollection.addMapping(childNode1.getId(), childNode1);
  }

  private static Stream<AddNodeTestScenario> generateTestCaseForAddNode() {
    //Test Case Valid Node
    AddNodeTestScenario testCase1 = AddNodeTestScenario.builder()
        .node(validNode)
        .output(true)
        .testCaseName("Adding valid node")
        .errMessage("")
        .build();

    //Test Case duplicate id Node
    AddNodeTestScenario testCase2 = AddNodeTestScenario.builder()
        .node(duplicateIdNode)
        .output(false)
        .testCaseName("Adding invalid node")
        .errMessage("Id already exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForAddNode")
  void testAddNode(AddNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node toAdd = testCase.getNode();
    boolean output = testCase.isOutput();
    try {
      nodeCollectionRepo.addNode(toAdd);
      assertEquals(output, nodeCollection.isNodePresent(toAdd.getId()), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<GetNodeTestScenario> generateTestCaseForGetNode() {
    //Test Case Getting valid node
    GetNodeTestScenario testCase1 = GetNodeTestScenario.builder()
        .node(parent)
        .id("3")
        .testCaseName("Getting valid node")
        .errMessage("")
        .build();

    //Test Case Getting invalid node
    GetNodeTestScenario testCase2 = GetNodeTestScenario.builder()
        .node(childNode1)
        .id("Invalid")
        .testCaseName("Getting invalid node")
        .errMessage("Id doesn't exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForGetNode")
  void testGetNode(GetNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    Node node = testCase.getNode();
    String id = testCase.getId();
    try {
      assertEquals(node, nodeCollectionRepo.getNode(id), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<RemoveNodeTestScenario> generateTestCaseForRemoveNode() {
    //Test Case Removing valid node
    RemoveNodeTestScenario testCase1 = RemoveNodeTestScenario.builder()
        .id("3")
        .testCaseName("Removing valid node")
        .errMessage("")
        .build();

    //Test Case Removing invalid node
    RemoveNodeTestScenario testCase2 = RemoveNodeTestScenario.builder()
        .id("Invalid")
        .testCaseName("Removing invalid node")
        .errMessage("Id doesn't exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForRemoveNode")
  void testRemoveNode(RemoveNodeTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    String id = testCase.getId();
    try {
      nodeCollectionRepo.removeNode(id);
      assertEquals(errMessage, "", testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

  private static Stream<IsNodePresentTestScenario> generateTestCaseForIsNodePresent() {
    //Test Case valid node
    IsNodePresentTestScenario testCase1 = IsNodePresentTestScenario.builder()
        .id("3")
        .testCaseName("valid node")
        .output(true)
        .errMessage("")
        .build();

    //Test Case invalid node
    IsNodePresentTestScenario testCase2 = IsNodePresentTestScenario.builder()
        .id("Invalid")
        .output(false)
        .testCaseName("invalid node")
        .errMessage("Id doesn't exist")
        .build();

    return Stream.of(testCase1, testCase2);
  }

  @ParameterizedTest
  @MethodSource("generateTestCaseForIsNodePresent")
  void testIsNodePresent(IsNodePresentTestScenario testCase) {
    String testCaseName = testCase.getTestCaseName();
    String errMessage = testCase.getErrMessage();
    String id = testCase.getId();
    boolean output = testCase.isOutput();
    try {
      assertEquals(output, nodeCollectionRepo.isNodePresent(id), testCaseName);
    } catch (Exception e) {
      assertEquals(errMessage, e.getMessage(), testCaseName);
    }
  }

}