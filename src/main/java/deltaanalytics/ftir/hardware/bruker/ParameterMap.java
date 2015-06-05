package deltaanalytics.ftir.hardware.bruker;

import java.util.LinkedHashMap;

public class ParameterMap {
    private LinkedHashMap ownHashMap = new LinkedHashMap();

    public void add(String key, String value) {
        ownHashMap.put(key, value);
    }

    public Object get(String key) {
        return ownHashMap.get(key);
    }
}
