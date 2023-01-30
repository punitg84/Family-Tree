package familytree.validations;

import familytree.models.Node;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidateNode {

  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  private static final Validator VALIDATOR = FACTORY.getValidator();

  public static void validateNode(final Node node) throws Exception {
    final Set<ConstraintViolation<Node>> violations = VALIDATOR.validate(node);
    for (final ConstraintViolation<Node> violation : violations) {
      throw new Exception(violation.getMessage());
    }
  }

  private static boolean isGivenChildPresent(Node parent,Node child){
    return false;
  }

  public static void validateGivenChildPresent(Node parent,Node child){

  }

  public static void validateGivenChildAbsent(Node parent,Node child){

  }

}
