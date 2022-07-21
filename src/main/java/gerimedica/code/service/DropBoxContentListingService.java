package gerimedica.code.service;

import gerimedica.code.util.TradeBotUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DropBoxContentListingService {

  public List<String> listFolderContents(final String extension) {
    String url = "https://api.dropboxapi.com/2/files/list_folder";
    long pageNo = 1;
    List<String> fileNames = new ArrayList<>();

    RestTemplate restTemplate = new RestTemplate();
    JSONObject response =
        new JSONObject(
            restTemplate
                .exchange(
                    url,
                    HttpMethod.POST,
                    getHttpEntity(TradeBotUtils.getNodeByName("list-folder")),
                    String.class)
                .getBody());

    // log.info("list folder response: {}", response.toString(4));
    printFiles(response, pageNo, fileNames);

    while (response.getBoolean("has_more")) {
      pageNo++;
      JSONObject pagedBody = new JSONObject();
      pagedBody.put("cursor", response.getString("cursor"));
      url = url + "/continue";

      response =
          new JSONObject(
              restTemplate
                  .exchange(url, HttpMethod.POST, getHttpEntity(pagedBody), String.class)
                  .getBody());
      // log.info("paginated response: {}", response.toString(4));

      printFiles(response, pageNo, fileNames);
    }

    log.info(".paper extension files\n");
    List<String> filteredFilesNames =
        fileNames.stream()
            .filter(name -> name.endsWith("." + extension))
            .collect(Collectors.toList());
    filteredFilesNames.forEach(
        name -> {
          log.info("{}", name);
        });

    log.info("total files with [{}] extension: [{}]\n", extension, filteredFilesNames.size());
    return filteredFilesNames;
  }

  private void printFiles(
      final JSONObject response, final long pageNo, final List<String> fileNames) {
    JSONArray entries = response.getJSONArray("entries");
    for (int i = 0; i < entries.length(); i++) {
      log.info("Page No: {}, FileName: {}", pageNo, entries.getJSONObject(i).getString("name"));
      fileNames.add(entries.getJSONObject(i).getString("name"));
    }
  }

  private HttpEntity getHttpEntity(JSONObject body) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    headers.add(
        HttpHeaders.AUTHORIZATION,
        "Bearer sl.BL2t9izoOYtOTnOw6mSyQhlAX8Ks0cRGd6wcDaQgOA6FbJcceJrbb_WnGXnUE8gClyrOjtCU_-FdXh1xQnrrhmLbfyFF8iLuw7mAkWyWGFYWWDdjdBpRMNW7yVOUIcphje2T7Y7-H7OK");
    return new HttpEntity<String>(body.toString(), headers);
  }
}
