package banquemisr.chanllenge05.finance.services;

import banquemisr.chanllenge05.finance.vo.JsonRequest;

public interface businessInterface {
    public abstract boolean validateItems(JsonRequest request);

    public abstract boolean doInsert(JsonRequest request);

    public abstract boolean doUpdate(JsonRequest request);

    public abstract boolean doDelete(JsonRequest request);
}
