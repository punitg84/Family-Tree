package familytree.models;

import static familytree.constants.ValidationMessage.ID_EMPTY_ERROR_MESSAGE;
import static familytree.constants.ValidationMessage.NAME_EMPTY_ERROR_MESSAGE;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Node {

  @NotEmpty(message = ID_EMPTY_ERROR_MESSAGE)
  private String id;

  @NotEmpty(message = NAME_EMPTY_ERROR_MESSAGE)
  private String name;

  @ToString.Exclude
  private Set<@NonNull Node> parent;

  @ToString.Exclude
  private Set<@NonNull Node> children;

  private Map<@NotEmpty String, @NotEmpty String> additionalInfo;

}
