package familytree.controllers;

import static familytree.validations.ValidateNode.validateNode;

import familytree.models.Node;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodeController {

  public boolean isChildPresent(final Node parent, final Node child) {
    return parent.getChildren().contains(child);
  }

  public boolean isParentPreset(final Node parent, final Node child) {
    return child.getParent().contains(parent);
  }

  public Set<Node> getParent(final Node node) {
    return node.getParent();
  }

  public Set<Node> getChildren(final Node node) {
    return node.getChildren();
  }

  public Node createNode(
      final String id,
      final String name,
      final Map<String, String> additionalInfo) throws Exception {

    final Node node = Node.builder()
        .name(name)
        .id(id)
        .additionalInfo(additionalInfo)
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    validateNode(node);

    return node;
  }

  public void addChild(final Node parent, final Node child) throws Exception {
    if (isChildPresent(parent, child)) {
      throw new Exception(String.format("Child with given id already exist %s", child.getId()));
    }

    parent.getChildren().add(child);
  }

  public void removeChild(final Node parent, final Node child) throws Exception {
    if (!isChildPresent(parent, child)) {
      throw new Exception(String.format("Child with given id doesn't exist %s", child.getId()));
    }

    parent.getChildren().remove(child);
  }

  public void addParent(final Node parent, final Node child) throws Exception {
    if (isParentPreset(parent, child)) {
      throw new Exception(String.format("Parent with given id already exist %s", parent.getId()));
    }

    child.getParent().add(parent);
  }

  public void removeParent(final Node parent, final Node child) throws Exception {
    if (!isParentPreset(parent, child)) {
      throw new Exception(String.format("Parent with given id doesn't exist %s", parent.getId()));
    }

    child.getParent().remove(parent);
  }

}
