package gerimedica.code.exception;

/**
 * Java Source CodeDataException.java created on Oct 9, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

public class CodeDataException extends CodeCommonBaseException {

    /**
     *
     */
    private static final long serialVersionUID = 3442147948166566118L;

    /**
     * @param errorCode
     * @param errorMessage
     */
    public CodeDataException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

}
