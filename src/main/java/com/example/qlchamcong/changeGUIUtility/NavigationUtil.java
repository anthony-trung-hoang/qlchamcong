package com.example.qlchamcong.changeGUIUtility;

import java.io.IOException;

public class NavigationUtil implements IActionChangeGUI {
    @Override
    public void changeGUI(String viewResource) throws IOException {
        NavigationManager.getInstance().changeGUI(viewResource);
    }
}