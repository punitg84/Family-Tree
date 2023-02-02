package familytree.controllers;

import familytree.models.Node;
import familytree.models.NodeCollection;

public class NodeCollectionRepo {

  private NodeCollection nodeCollection;

  public NodeCollectionRepo(NodeCollection nodeCollection) {
    this.nodeCollection = nodeCollection;
  }

  public boolean isNodePresent(String id) {
    return nodeCollection.getNodeMap().containsKey(id);
  }

  public void addNode(Node node) throws Exception {
    String id = node.getId();

    if (isNodePresent(id)) {
      throw new Exception(String.format("ID already exist : %s", id));
    }

    nodeCollection.getNodeMap().put(id, node);
  }

  public Node getNode(String id) throws Exception {
    if (!isNodePresent(id)) {
      throw new Exception(String.format("ID doesn't exist : %s", id));
    }

    return nodeCollection.getNodeMap().get(id);
  }

  public void removeNode(String id) throws Exception {
    if (!isNodePresent(id)) {
      throw new Exception(String.format("ID doesn't exist : %s", id));
    }

    nodeCollection.getNodeMap().remove(id);
  }

}
