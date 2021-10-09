package gerimedica.code.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gerimedica.code.dto.CodeData;

/**
 * Java Source CodeRepository.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

@Repository
public interface CodeRepository extends CrudRepository<CodeData, Long> {
    // I can add custom methods here
}
