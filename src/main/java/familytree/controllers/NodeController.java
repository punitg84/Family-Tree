package familytree.controllers;

import familytree.models.Node;
import java.util.List;
import java.util.Set;

public class NodeController {

  public boolean isDependencyPresent(Node parent,Node child){
    return false;
  }

  public Set<Node> getParent(Node node){
    // return its parent
    return null;
  }

  public Set<Node> getChildren(Node node){
    // return its children
    return null;
  }

  public Node createNode(String id,String name, List<String> additionalInfo){
    //return object of node
    return null;
  }

  public void addChild(Node parent,Node child){
    //Perform validation that no such relation exist
    //add it
  }

  public void removeChild(Node parent,Node child){
    //Perform validation that there is a children present
    //remove it
  }

  public void removeParent(Node node){
    //remove parent to null
  }

}
