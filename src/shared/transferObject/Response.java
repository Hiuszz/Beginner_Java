package shared.transferObject;

import java.io.Serializable;

public class Response implements Serializable {
    private String type;
    private Object arg;

    public Response(String type, Object arg) {
        this.type = type;
        this.arg = arg;
    }

    public String getType() {
        return type;
    }

    public Object getArg() {
        return arg;
    }
}
