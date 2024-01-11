package com.example.qlchamcong.HRSystem;
public class HRSystemInitializer {
    private static IHRSystemAPIService ihrSystemAPIService;

    public HRSystemInitializer() {
        ihrSystemAPIService = new HRSystemAPIService();
    }

        public static IHRSystemAPIService getIhrSystemAPIService() {
        return ihrSystemAPIService;
    }
}
