package gerimedica.code.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Java Source CodeExceptionHandler.java created on Oct 9, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */
@ControllerAdvice
public class CodeExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * @param ex
   * @return
   */
  @ExceptionHandler(value = {CodeCommonBaseException.class})
  public ResponseEntity<CodeExceptionResponseDTO> handleCommonBaseException(
      CodeCommonBaseException ex) {

    log.error("error", ex);
    return new ResponseEntity<>(
        new CodeExceptionResponseDTO(ex.getErrorCode(), ex.getErrorMessage()),
        HttpStatus.valueOf(ex.getErrorCode()));
  }

  /**
   * @param th
   * @return
   */
  @ExceptionHandler(value = {Throwable.class})
  public ResponseEntity<CodeExceptionResponseDTO> handleException(Throwable th) {

    log.error("error", th);
    return new ResponseEntity<>(
        new CodeExceptionResponseDTO("500", th.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // add more custom exception handler and return http error status, error
  // code chained etc

}
