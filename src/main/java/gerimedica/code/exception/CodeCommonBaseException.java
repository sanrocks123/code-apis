package gerimedica.code.exception;

/**
 * Java Source CodeCommonBaseException.java created on Oct 9, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */
public class CodeCommonBaseException extends RuntimeException {

  /** */
  private static final long serialVersionUID = -7972389955669366720L;

  private final String errorCode;
  private final String errorMessage;

  /** */
  public CodeCommonBaseException(String errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @return the errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }
}
