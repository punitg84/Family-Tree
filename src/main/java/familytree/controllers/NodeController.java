package familytree.controllers;

import familytree.models.Node;
import java.util.List;
import java.util.Set;

public class NodeController {

  List<Node> getParent(Node node){
    // return its parent
    return null;
  }

  List<Node> getChildren(Node node){
    // return its children
    return null;
  }

  Node createNode(String id,String name, List<String> additionalInfo){
    //return object of node
    return null;
  }

  void addChild(Node parent,Node child){
    //Perform validation that no such relation exist
    //add it
  }

  void removeChild(Node parent,Node child){
    //Perform validation that there is a children present
    //remove it
  }

  void removeParent(Node node){
    //remove parent to null
  }

}
