package gerimedica.code.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gerimedica.code.dto.CodeData;
import gerimedica.code.repository.CodeRepository;

/**
 * Java Source CodeServiceImpl.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */
@Service
public class CodeServiceImpl implements CodeService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired private CodeRepository codeRepository;

  /*
   * (non-Javadoc)
   *
   * @see gerimedica.code.service.CodeService#deleteAll()
   */
  @Override
  public List<CodeData> deleteAll() {
    final List<CodeData> result = fetchAll();
    codeRepository.deleteAll();
    final long count = codeRepository.count();

    if (count == 0) {
      log.info("deleteAll, after delete entity count: {}", count);
      return result;
    }

    log.warn("deleteAll, seems few entities could not be deleted, count : {}", count);
    return Collections.emptyList();
  }

  /*
   * (non-Javadoc)
   *
   * @see gerimedica.code.service.CodeService#fetchAll()
   */
  @Override
  public List<CodeData> fetchAll() {
    final List<CodeData> result = new ArrayList<>();
    codeRepository
        .findAll()
        .forEach(
            d -> {
              result.add(d);
            });

    // we can use pagination sorting interface as well, but keeping it as
    // simple MVP

    log.info("fetchAll, result : {}", result);
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see gerimedica.code.service.CodeService#fetchByCode(java.lang.String)
   */
  @Override
  public CodeData fetchByCode(String code) {

    CodeData data = codeRepository.findByCode(code);
    log.info("findByCode: {}", data);

    final Optional<CodeData> result =
        StreamSupport.stream(codeRepository.findAll().spliterator(), true)
            .filter(x -> x.getCode().equals(code))
            .findAny();

    log.info("fetchByCode, code: {},  result : {}", code, result);

    if (result.isPresent()) {
      log.info("fetchByCode, entity found : {}", result.get());
      return result.get();
    }

    log.warn("fetchByCode, entity not found for code : {}", code);
    return null; // can throw custom CodeNotFoundException as well
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * gerimedica.code.service.CodeService#create(gerimedica.code.dto.CodeData)
   */
  @Override
  public CodeData upload(CodeData code) {
    log.info("upload,  codedata request : {}", code);

    final CodeData response = codeRepository.save(code);
    log.info("upload, db response : {}", response);
    return response;
  }
}
