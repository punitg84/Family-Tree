package familytree.controllers;

import familytree.models.Node;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FamilyTreeController {

  private final NodeCollectionRepo nodeCollectionRepo;
  private final NodeController nodeController;

  public FamilyTreeController(
      final NodeCollectionRepo nodeCollectionRepo,
      final NodeController nodeController) {

    this.nodeCollectionRepo = nodeCollectionRepo;
    this.nodeController = nodeController;
  }

  public Set<Node> getParent(final String id) throws Exception {
    final Node currNode = nodeCollectionRepo.getNode(id);

    return nodeController.getParent(currNode);
  }

  public Set<Node> getChildren(final String id) throws Exception {
    final Node currNode = nodeCollectionRepo.getNode(id);

    return nodeController.getChildren(currNode);
  }

  public Set<Node> getAncestors(final String id) throws Exception {
    final Node currNode = nodeCollectionRepo.getNode(id);

    final Set<Node> ancestors = new HashSet<>();

    final Queue<Node> queue = new ArrayDeque<>();
    queue.add(currNode);

    while (!queue.isEmpty()) {
      final Node child = queue.poll();
      for (final Node parent : nodeController.getParent(child)) {
        if (!ancestors.contains(parent)) {
          ancestors.add(parent);
          queue.add(parent);
        }
      }
    }

    return ancestors;
  }

  public Set<Node> getDescendants(final String id) throws Exception {
    final Node currNode = nodeCollectionRepo.getNode(id);

    final Set<Node> descendants = new HashSet<>();

    final Queue<Node> queue = new ArrayDeque<>();
    queue.add(currNode);

    while (!queue.isEmpty()) {
      final Node parent = queue.poll();
      for (final Node child : nodeController.getChildren(parent)) {
        if (!descendants.contains(child)) {
          descendants.add(child);
          queue.add(child);
        }
      }
    }

    return descendants;
  }

  public void deleteDependency(final String parentId, final String childId) throws Exception {
    final Node parent = nodeCollectionRepo.getNode(parentId);
    final Node child = nodeCollectionRepo.getNode(childId);

    nodeController.removeChild(parent, child);
    nodeController.removeParent(parent, child);
  }

  public void deleteNode(final String id) throws Exception {
    final Node node = nodeCollectionRepo.getNode(id);

    for (final Node child : nodeController.getChildren(node)) {
      nodeController.removeParent(node, child);
    }

    for (final Node parent : nodeController.getParent(node)) {
      nodeController.removeChild(parent, node);
    }

    nodeCollectionRepo.removeNode(id);
  }

  public void addNode(
      final String id,
      final String name,
      final Map<String, String> additionalInfo) throws Exception {

    final Node node = nodeController.createNode(id, name, additionalInfo);

    nodeCollectionRepo.addNode(node);
  }

  public void addDependency(final String parentId, final String childId) throws Exception {
    final Node parent = nodeCollectionRepo.getNode(parentId);
    final Node child = nodeCollectionRepo.getNode(childId);

    if (isCyclicDependency(parent, child)) {
      throw new Exception(
          String.format("Dependency is cyclic between %1$s and %2$s", parentId, childId));
    }

    nodeController.addParent(parent, child);
    nodeController.addChild(parent, child);
  }

  public boolean isCyclicDependency(final Node parent, final Node child) throws Exception {
    return getDescendants(child.getId()).contains(parent);
  }

}
