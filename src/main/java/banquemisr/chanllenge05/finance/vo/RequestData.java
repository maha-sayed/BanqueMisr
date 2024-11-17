package banquemisr.chanllenge05.finance.vo;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
@Data
@RequestScope
@Component
public class RequestData {

        private Integer messageCode;
        private Integer messageType;
        // 0 Ok
        // 1 ERROR
        // 2 WARNING
        // 3 ALERT
        // 4 Notification
        private String messageDescr;
}
