package gerimedica.code.service;

import gerimedica.code.dto.DropBoxRequest;
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

  public List<String> listFolderContents(final DropBoxRequest dropBoxRequest) {
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
                    getHttpEntity(TradeBotUtils.getNodeByName("list-folder"), dropBoxRequest),
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
                  .exchange(
                      url, HttpMethod.POST, getHttpEntity(pagedBody, dropBoxRequest), String.class)
                  .getBody());
      // log.info("paginated response: {}", response.toString(4));

      printFiles(response, pageNo, fileNames);
    }

    log.info(".paper extension files\n");
    List<String> filteredFilesNames =
        fileNames.stream()
            .filter(name -> name.endsWith("." + dropBoxRequest.getFilterFileExtensions()))
            .collect(Collectors.toList());
    filteredFilesNames.forEach(
        name -> {
          log.info("{}", name);
        });

    log.info(
        "total files with [{}] extension: [{}]\n",
        dropBoxRequest.getFilterFileExtensions(),
        filteredFilesNames.size());
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

  private HttpEntity getHttpEntity(JSONObject body, final DropBoxRequest dropBoxRequest) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    headers.add(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", dropBoxRequest.getToken()));
    return new HttpEntity<String>(body.toString(), headers);
  }
}
