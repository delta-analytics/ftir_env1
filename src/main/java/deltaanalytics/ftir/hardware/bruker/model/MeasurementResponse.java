package deltaanalytics.ftir.hardware.bruker.model;

public class MeasurementResponse {
    private String rawResponse;

    public MeasurementResponse(String rawResponse){
        this.rawResponse = rawResponse;
    }

    public String getDAP() {
        String fileWithPath = rawResponse.split(" ")[2];
        return fileWithPath.substring(1, fileWithPath.lastIndexOf("\\"));
    }

    public String getSAN() {
        String fileWithPath = rawResponse.split(" ")[2];
        return fileWithPath.substring(fileWithPath.lastIndexOf("\\")+1, fileWithPath.length()-1).trim();
    }
    
    public String getFileId(){
        return rawResponse.split(" ")[4];
    }

}
