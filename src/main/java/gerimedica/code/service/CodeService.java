package gerimedica.code.service;

import java.util.List;

import gerimedica.code.dto.CodeData;

/**
 * Java Source CodeService.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

public interface CodeService {

    /**
     *
     * @return
     */
    List<CodeData> deleteAll();

    /**
     *
     * @return
     */
    List<CodeData> fetchAll();

    /**
     *
     * @param code
     * @return
     */
    CodeData fetchByCode(String code);

    /**
     * @param code
     * @return
     */
    CodeData upload(CodeData code);

}
