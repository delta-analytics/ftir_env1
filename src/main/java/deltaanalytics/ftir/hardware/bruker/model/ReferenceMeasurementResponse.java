package deltaanalytics.ftir.hardware.bruker.model;

public class ReferenceMeasurementResponse {
    private String rawResponse;

    public ReferenceMeasurementResponse(String rawResponse){
        this.rawResponse = rawResponse;
    }

    public boolean successfull(){
        return rawResponse.split(" ")[0].equalsIgnoreCase("OK");
    }

    public String getErrorCode(){
        return rawResponse;
    }
}
