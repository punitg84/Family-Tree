package familytree.controllers;

import familytree.models.Node;
import java.util.List;

public class NodeCollectionController {

  void addNode(String id,String name, List<String> additionalInfo){
    //Validate id absent
    //create node in node controller
  }

  Node getNode(String id){
    //Validate id present
    //return node
    return null;
  }

  void removeNode(String id){
    //Check id present
    //Remove pointer from mapping
  }
}
