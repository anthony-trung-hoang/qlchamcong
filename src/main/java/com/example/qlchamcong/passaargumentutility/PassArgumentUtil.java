package com.example.qlchamcong.passaargumentutility;

import com.example.qlchamcong.changeGUIUtility.NavigationManager;

public class PassArgumentUtil implements IPassArgument {
    @Override
    public void setSharedData(String key, Object sharedData) {
        ArgumentStore.getInstance().setSharedData(key, sharedData);
    }
    public Object getSharedData(String key) {
        return ArgumentStore.getInstance().getSharedData(key);
    }
}
