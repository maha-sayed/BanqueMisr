package banquemisr.chanllenge05.finance.vo;

import lombok.Data;

import java.util.List;

@Data
public class Metadata {
    private Integer usrno;
    private String funcMode;
    private int roleId;
    private String langCode;
    private List<String> calcObject;
}
