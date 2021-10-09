package gerimedica.code.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gerimedica.code.dto.CodeData;
import gerimedica.code.exception.CodeDataException;
import gerimedica.code.service.CodeService;
import io.swagger.annotations.Api;

/**
 * Java Source CodeApiController.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

@RestController
@Api(value = "Code Service")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeApiController {

    @Autowired
    private CodeService codeService;

    /**
     *
     * @param code
     * @return
     */
    @PostMapping("/code-data")
    public ResponseEntity<CodeData> create(@RequestBody CodeData code) {

        // body payload validation just for example, check out error handling
        // exception handler advice
        if (StringUtils.isEmpty(code.getCode())) {
            throw new CodeDataException(HttpStatus.BAD_REQUEST.name(), "missing code attribute");
        }

        return new ResponseEntity<CodeData>(codeService.upload(code), HttpStatus.CREATED);
    }

    /**
     *
     * @return
     */
    @DeleteMapping("/code-data/all")
    public ResponseEntity<List<CodeData>> deleteAll() {
        final List<CodeData> result = codeService.deleteAll();
        return new ResponseEntity<>(result, result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/code-data/all")
    public ResponseEntity<List<CodeData>> fetchAll() {
        final List<CodeData> result = codeService.fetchAll();
        return new ResponseEntity<>(result, result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/code-data/{code}")
    public ResponseEntity<CodeData> fetchByCode(@PathVariable(name = "code") String code) {

        final CodeData codedata = codeService.fetchByCode(code);

        if (Objects.isNull(codedata)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(codedata, HttpStatus.NOT_FOUND);
    }

}
