package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private String message;
    private LocalDateTime executionStartedAt;
    private LocalDateTime executionFinishedAt;
    private List<BrukerConfigurationParameter> brukerConfigurationParameterList = new ArrayList<>();

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setExecutionStartedAt(LocalDateTime executionStartedAt) {
        this.executionStartedAt = executionStartedAt;
    }

    public LocalDateTime getExecutionStartedAt() {
        return executionStartedAt;
    }

    public void setExecutionFinishedAt(LocalDateTime executionFinishedAt) {
        this.executionFinishedAt = executionFinishedAt;
    }

    public LocalDateTime getExecutionFinishedAt() {
        return executionFinishedAt;
    }

    public void setBrukerConfigurationParameterList(List<BrukerConfigurationParameter> parameterMaps) {
        this.brukerConfigurationParameterList = parameterMaps;
    }

    public List<BrukerConfigurationParameter> getBrukerConfigurationParameterList() {
        return brukerConfigurationParameterList;
    }

    //ToDo Hier wird der komplette Request gebaut, momentan ohne ParameterMap
    //Beispiel: http://localhost/OpusCommand.htm?MeasureSample (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    //Message + params
    public String buildCompleteMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message);
        stringBuilder.append(" (0, {");
        for (BrukerConfigurationParameter brukerConfigurationParameter : brukerConfigurationParameterList) {
            stringBuilder.append(brukerConfigurationParameter.getKey());
            stringBuilder.append("=");
            stringBuilder.append("\'");
            stringBuilder.append(brukerConfigurationParameter.getValue());
            stringBuilder.append("\'");
            stringBuilder.append(", ");
        }
        stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length(),"");
        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
