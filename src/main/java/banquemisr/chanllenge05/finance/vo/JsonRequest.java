package banquemisr.chanllenge05.finance.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@NoArgsConstructor
public class JsonRequest {
    private Metadata metadata;
    private LinkedList<Object> objectdata;
}
