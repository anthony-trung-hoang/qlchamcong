package com.example.qlchamcong.changeGUIUtility;

import java.io.IOException;

public interface IActionChangeGUI {
    void changeGUI(String viewResource) throws IOException;

    void showModal(String modalResource, String modalTitle) throws IOException;

    void closeModal();
}
