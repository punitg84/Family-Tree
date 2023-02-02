package familytree.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

@Getter
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

  public void clearMapping() {
    nodeMap.clear();
  }

}
