package com.example.qlchamcong.changeGUIUtility;

import java.io.IOException;

public class NavigationUtil implements IActionChangeGUI {
    @Override
    public void changeGUI(String viewResource) throws IOException {
        NavigationManager.getInstance().changeGUI(viewResource);
    }

    @Override
    public void showModal(String modalResource, String modalTitle) throws IOException {
        NavigationManager.getInstance().showModal(modalResource, modalTitle);
    }

    @Override
    public void closeModal() {
        NavigationManager.getInstance().closeModal();
    }


}