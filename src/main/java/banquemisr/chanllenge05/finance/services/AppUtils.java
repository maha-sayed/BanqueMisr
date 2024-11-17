package banquemisr.chanllenge05.finance.services;

import org.springframework.stereotype.Service;

@Service
public class AppUtils {
    public boolean isNotNullValidator(Object value) {
        boolean res = false;
        if (null != value && !value.equals("")) {
            res = !value.getClass().getName().equals("java.lang.String") || !value.toString().trim().equals("");
        }
        return res;
    }}
