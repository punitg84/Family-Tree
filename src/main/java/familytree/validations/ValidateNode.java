package familytree.validations;

import familytree.models.Node;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public final class ValidateNode {

  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  private static final Validator VALIDATOR = FACTORY.getValidator();

  private ValidateNode() {

  }

  public static void validateNode(final Node node) throws Exception {
    final Set<ConstraintViolation<Node>> violations = VALIDATOR.validate(node);
    for (final ConstraintViolation<Node> violation : violations) {
      throw new Exception(
          String.format("%1$s, value : %2$s", violation.getMessage(), violation.getInvalidValue()));
    }
  }

}
