package gerimedica.code.controller;

import gerimedica.code.service.DropBoxContentListingService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dropbox")
@RequiredArgsConstructor
public class DropBoxController {

  private final DropBoxContentListingService dropBox;

  @GetMapping("/list-folder")
  public ResponseEntity<Map<String, Object>> listFolderContents(
      @RequestParam(defaultValue = "paper", required = false) String extension) {

    List<String> contents = dropBox.listFolderContents(extension);
    Map<String, Object> response = new HashMap<>();
    response.put("count", contents.size());
    response.put("files", contents);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
