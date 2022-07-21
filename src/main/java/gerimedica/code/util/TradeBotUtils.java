package gerimedica.code.util;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 * Java Source TradeBotUtils created on 12/23/2021
 *
 * @author : Sanjeev Saxena
 * @version : 1.0
 * @email : sanrocks123@gmail.com
 */
@Slf4j
public class TradeBotUtils {

  /**
   * @param key
   * @return
   */
  public static JSONObject getNodeByName(final String key) {
    try {
      InputStream in = TradeBotUtils.class.getResourceAsStream("/trade.json");
      String tradeJson = IOUtils.toString(in, "UTF-8");
      return new JSONObject(tradeJson).getJSONObject(key);

    } catch (IOException e) {
      log.error("error reading request file", e);
      throw new IllegalArgumentException(String.format("error getting node", e.getMessage()));
    }
  }
}
