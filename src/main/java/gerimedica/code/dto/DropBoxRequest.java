package gerimedica.code.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class DropBoxRequest implements Serializable {

  private String token;
  private String filterFileExtensions;
}
