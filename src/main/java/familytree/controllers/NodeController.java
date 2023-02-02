package familytree.controllers;

import static familytree.validations.ValidateNode.validateNode;

import familytree.models.Node;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodeController {

  public boolean isChildPresent(Node parent, Node child){
    return parent.getChildren().contains(child);
  }

  public boolean isParentPreset(Node parent, Node child){
    return child.getParent().contains(parent);
  }

  public Set<Node> getParent(Node node){
    return node.getParent();
  }

  public Set<Node> getChildren(Node node){
    return node.getChildren();
  }

  public Node createNode(String id,String name, Map<String,String> additionalInfo)
      throws Exception {
    Node node = Node.builder()
        .name(name)
        .id(id)
        .additionalInfo(additionalInfo)
        .parent(new HashSet<>())
        .children(new HashSet<>())
        .build();

    validateNode(node);

    return node;
  }

  public void addChild(Node parent,Node child) throws Exception {
    if(isChildPresent(parent,child)){
      throw new Exception(String.format("Child with given id already exist %s",child.getId()));
    }

    parent.getChildren().add(child);
  }

  public void removeChild(Node parent,Node child) throws Exception {
    if(!isChildPresent(parent,child)){
      throw new Exception(String.format("Child with given id doesn't exist %s",child.getId()));
    }

    parent.getChildren().remove(child);
  }

  public void addParent(Node parent,Node child) throws Exception {
    if(isParentPreset(parent,child)){
      throw new Exception(String.format("Parent with given id already exist %s",parent.getId()));
    }

    child.getParent().add(parent);
  }

  public void removeParent(Node parent,Node child) throws Exception {
    if(!isParentPreset(parent,child)){
      throw new Exception(String.format("Parent with given id doesn't exist %s",parent.getId()));
    }

    child.getParent().remove(parent);
  }

}
