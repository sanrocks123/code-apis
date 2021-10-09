package gerimedica.code.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;

/**
 * Java Source CodeData.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

@Entity
public class CodeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String source;
    private String codeListCode;

    // As I can see in csv data file, code can be alphanumeric so making it
    // String data type
    private String code;

    private String displayValue;
    private String longDescription;

    // we can use LocalDate object as well but keeping it simple for now
    private String fromDate;

    // we can have enum of priorities as well but keeping it simple for now
    private int sortingPriority;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the codeListCode
     */
    public String getCodeListCode() {
        return codeListCode;
    }

    /**
     * @return the displayValue
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * @return the sortingPriority
     */
    public int getSortingPriority() {
        return sortingPriority;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param codeListCode
     *            the codeListCode to set
     */
    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    /**
     * @param displayValue
     *            the displayValue to set
     */
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * @param fromDate
     *            the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param longDescription
     *            the longDescription to set
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * @param sortingPriority
     *            the sortingPriority to set
     */
    public void setSortingPriority(int sortingPriority) {
        this.sortingPriority = sortingPriority;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
