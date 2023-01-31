package familytree.controllers;

import familytree.models.Node;
import familytree.models.NodeCollection;

public class NodeCollectionRepo {

  private NodeCollection nodeCollection;

  public NodeCollectionRepo(NodeCollection nodeCollection){
    this.nodeCollection = nodeCollection;
  }

  private boolean isNodePresent(String id){
    return false;
  }

  public void addNode(Node node){
    //isNodePresent
    //Store in model
  }

  public Node getNode(String id){
    //Validate id present
    //return node
    return null;
  }

  public void removeNode(String id){
    //Check id present
    //Remove pointer from mapping
  }
}
