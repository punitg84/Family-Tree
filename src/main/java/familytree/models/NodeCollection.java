package familytree.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NodeCollection {

  private static NodeCollection nodeCollectionInstance;
  private Map<String, Node> nodeMap;

  private NodeCollection() {
    nodeMap = new HashMap<>();
  }

  public static NodeCollection getInstance() {
    if (Objects.isNull(nodeCollectionInstance)) {
      nodeCollectionInstance = new NodeCollection();
    }
    return nodeCollectionInstance;
  }

  public void addMapping(String id, Node node) {
    nodeMap.put(id, node);
  }

  public void removeMapping(String id) {
    nodeMap.remove(id);
  }

  public boolean isNodePresent(String id) {
    return nodeMap.containsKey(id);
  }

  public void clearMapping() {
    nodeMap.clear();
  }

}
