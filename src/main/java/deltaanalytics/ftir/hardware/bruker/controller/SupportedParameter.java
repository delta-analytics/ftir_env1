package deltaanalytics.ftir.hardware.bruker.controller;

public enum SupportedParameter {
    EXP("EXP"), XPP("XPP"), ADT("ADT"),
    APF("APF"), APT("APT"), AQM("AQM"),
    BMS("BMS"), BSF("BSF"), CHN("CHN"),
    CNM("CNM"), DEL("DEL"), DIG("DIG"),
    DLY("DLY"), DMX("DMX"), DTC("DTC"),
    GSG("GSG"), HFQ("HFQ"), HFW("HFW"),
    IRS("IRS"), HPF("HPF"), LFQ("LFQ"),
    LFW("LFW"), LPF("LPF"), MIN("MIN");

    private String key;

    SupportedParameter(String key) {
        this.key = key;
    }

    public static boolean isSupported(String givenKey) {
        boolean supported = false;
        for (SupportedParameter supportedParameter : SupportedParameter.values()) {
            if (supportedParameter.key.equalsIgnoreCase(givenKey)) {
                supported = true;
                break;
            }
        }
        return supported;
    }
}

