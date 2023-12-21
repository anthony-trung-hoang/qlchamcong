package com.example.qlchamcong.passaargumentutility;

public interface IPassArgument {
    void setSharedData(String key, Object sharedData);
    Object getSharedData(String key);
}
