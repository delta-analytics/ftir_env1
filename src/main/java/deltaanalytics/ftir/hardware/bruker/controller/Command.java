package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;

import java.time.LocalDateTime;
import java.util.List;

public class Command {
    private String message;
    private LocalDateTime executionStartedAt;
    private LocalDateTime executionFinishedAt;
    private List<BrukerConfigurationParameter> brukerConfigurationParameterList;

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
    public String buildCompleteMessage() {
        return message;
    }
}
