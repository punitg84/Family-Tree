package familytree.controllers;

import familytree.models.Node;
import familytree.models.NodeCollection;

public class NodeCollectionRepo {

  private final NodeCollection nodeCollection;

  public NodeCollectionRepo(final NodeCollection nodeCollection) {
    this.nodeCollection = nodeCollection;
  }

  public boolean isNodePresent(final String id) {
    return nodeCollection.getNodeMap().containsKey(id);
  }

  public void addNode(final Node node) throws Exception {
    final String id = node.getId();

    if (isNodePresent(id)) {
      throw new Exception(String.format("ID already exist : %s", id));
    }

    nodeCollection.getNodeMap().put(id, node);
  }

  public Node getNode(final String id) throws Exception {
    if (!isNodePresent(id)) {
      throw new Exception(String.format("ID doesn't exist : %s", id));
    }

    return nodeCollection.getNodeMap().get(id);
  }

  public void removeNode(final String id) throws Exception {
    if (!isNodePresent(id)) {
      throw new Exception(String.format("ID doesn't exist : %s", id));
    }

    nodeCollection.getNodeMap().remove(id);
  }

}
