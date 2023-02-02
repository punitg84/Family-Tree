package familytree.controllers;

import familytree.models.Node;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FamilyTreeController {

  private NodeCollectionRepo nodeCollectionRepo;
  private NodeController nodeController;

  public FamilyTreeController(
      NodeCollectionRepo nodeCollectionRepo,
      NodeController nodeController) {

    this.nodeCollectionRepo = nodeCollectionRepo;
    this.nodeController = nodeController;
  }

  public Set<Node> getParent(String id) throws Exception {
    Node currNode = nodeCollectionRepo.getNode(id);

    return nodeController.getParent(currNode);
  }

  public Set<Node> getChildren(String id) throws Exception {
    Node currNode = nodeCollectionRepo.getNode(id);

    return nodeController.getChildren(currNode);
  }

  public Set<Node> getAncestors(String id) throws Exception {
    Node currNode = nodeCollectionRepo.getNode(id);

    Set<Node> ancestors = new HashSet<>();

    Queue<Node> queue = new ArrayDeque<>();
    queue.add(currNode);

    while (!queue.isEmpty()) {
      Node child = queue.poll();
      for (Node parent : nodeController.getParent(child)) {
        if (!ancestors.contains(parent)) {
          ancestors.add(parent);
          queue.add(parent);
        }
      }
    }

    return ancestors;
  }

  public Set<Node> getDescendants(String id) throws Exception {
    Node currNode = nodeCollectionRepo.getNode(id);

    Set<Node> descendants = new HashSet<>();

    Queue<Node> queue = new ArrayDeque<>();
    queue.add(currNode);

    while (!queue.isEmpty()) {
      Node parent = queue.poll();
      for (Node child : nodeController.getChildren(parent)) {
        if (!descendants.contains(child)) {
          descendants.add(child);
          queue.add(child);
        }
      }
    }

    return descendants;
  }

  public void deleteDependency(String parentId, String childId) throws Exception {
    Node parent = nodeCollectionRepo.getNode(parentId);
    Node child = nodeCollectionRepo.getNode(childId);

    nodeController.removeChild(parent, child);
    nodeController.removeParent(parent, child);
  }

  public void deleteNode(String id) throws Exception {
    Node node = nodeCollectionRepo.getNode(id);

    for (Node child : nodeController.getChildren(node)) {
      nodeController.removeParent(node, child);
    }

    for (Node parent : nodeController.getParent(node)) {
      nodeController.removeChild(parent, node);
    }

    nodeCollectionRepo.removeNode(id);
  }

  public void addNode(String id, String name, Map<String, String> additionalInfo) throws Exception {
    Node node = nodeController.createNode(id, name, additionalInfo);

    nodeCollectionRepo.addNode(node);
  }

  public void addDependency(String parentId, String childId) throws Exception {
    Node parent = nodeCollectionRepo.getNode(parentId);
    Node child = nodeCollectionRepo.getNode(childId);

    if (isCyclicDependency(parent, child)) {
      throw new Exception(
          String.format("Dependency is cyclic between %1$s and %2$s", parentId, childId));
    }

    nodeController.addParent(parent, child);
    nodeController.addChild(parent, child);
  }

  public boolean isCyclicDependency(Node parent, Node child) throws Exception {
    Set<Node> store = getDescendants(child.getId());
    return store.contains(parent);
  }

}
