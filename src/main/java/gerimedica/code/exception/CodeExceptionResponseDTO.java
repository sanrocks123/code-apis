package gerimedica.code.exception;

import java.io.Serializable;

/**
 * Java Source CodeExceptionResponseDTO.java created on Oct 9, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

public class CodeExceptionResponseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5412393802640571302L;

    private String errorMessage;
    private String errorCode;

    /**
     *
     */
    public CodeExceptionResponseDTO(String errorCode, String errorMessage) {
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

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
