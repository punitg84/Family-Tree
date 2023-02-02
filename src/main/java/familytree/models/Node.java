package familytree.models;

import static familytree.constants.ValidationMessage.ID_EMPTY_MESSAGE;
import static familytree.constants.ValidationMessage.NAME_EMPTY_MESSAGE;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Node {

  @NotEmpty(message = ID_EMPTY_MESSAGE)
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;

  @NotEmpty(message = NAME_EMPTY_MESSAGE)
  @ToString.Include
  private String name;

  private Set<@NonNull Node> parent;

  private Set<@NonNull Node> children;

  @ToString.Include
  private Map<@NotEmpty String,@NotEmpty String> additionalInfo;

}
