package familytree.controllers;

import familytree.models.Node;
import java.util.HashMap;
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

  public Set<Node> getParent(String id) {
    //node collection controller to fetch the node
    //node controller to get parent
    return null;
  }

  public Set<Node> getChildren(String id) {
    //node collection controller to fetch the node
    //node controller to get children
    return null;
  }

  public Set<Node> getAncestors(String id) {
    //node collection controller to fetch the node
    //BFS on parent chala do jab tak top par ni pohoch jaye
    //list mein dalke de do
    return null;
  }

  public Set<Node> getDescendants(String id) {
    //node collection controller to fetch the node
    //BFS on child chala do jab tak niche ni pohoch jaye
    //list mein dalke de do
    return null;
  }

  public void deleteDependency(String parentId, String childrenId) {
    //Fetch the node from node collection controller
    //Call remove children in node controller and then call remove parent
  }

  public void deleteNode(String id) {
    //Fetch the node from node collection controller
    //Fetch children for the given node using node controller
    //Now iterate one by one and delete its parent from node controller
    //atlast remove mapping from node collection controller
  }

  public void addNode(String id, String name, HashMap<String,String> additionalInfo) {
    //call add node from node collection controller
  }

  public void addDependency(String parentId, String childrenId) {
    //Fetch child and parent node
    //Traverse descendants of child
    //if parents found throw exception if not add the dependency
  }

  public boolean isCyclicDependency(Node parent,Node child){
    return false;
  }

}
