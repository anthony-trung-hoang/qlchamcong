package com.example.qlchamcong.passaargumentutility;

import java.util.HashMap;
import java.util.Map;

public class ArgumentStore {
    private static ArgumentStore instance;
    private Map<String, Object> sharedDataMap;

    private ArgumentStore() {
        sharedDataMap = new HashMap<>();
    }

    public static ArgumentStore getInstance() {
        if (instance == null) {
            instance = new ArgumentStore();
        }
        return instance;
    }

    public void setSharedData(String key, Object sharedData) {
        sharedDataMap.put(key, sharedData);
    }

    public Object getSharedData(String key) {
        return sharedDataMap.get(key);
    }
}
