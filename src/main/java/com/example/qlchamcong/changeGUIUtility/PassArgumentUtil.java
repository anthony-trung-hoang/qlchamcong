package com.example.qlchamcong.changeGUIUtility;

public class PassArgumentUtil implements IPassArgument {
    @Override
    public void setSharedData(String key, Object sharedData) {
        NavigationManager.getInstance().setSharedData(key, sharedData);
    }

    public Object getSharedData(String key) {
        return NavigationManager.getInstance().getSharedData(key);
    }
}
