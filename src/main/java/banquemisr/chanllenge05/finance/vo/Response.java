package banquemisr.chanllenge05.finance.vo;

import lombok.Data;

import java.util.LinkedList;

@Data
public class Response {
    private Integer messageCode;
    private Integer messageType;
    // 0 Ok
    // 1 ERROR
    // 2 WARNING
    // 3 ALERT
    // 4 Notification
    private String messageDescr;
    private LinkedList<Object> objectdata;
}
