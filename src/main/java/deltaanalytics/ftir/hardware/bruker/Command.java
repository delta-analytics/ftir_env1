package deltaanalytics.ftir.hardware.bruker;

import java.time.LocalDateTime;
import java.util.List;

public class Command {
    private String message;
    private LocalDateTime executionStartedAt;
    private LocalDateTime executionFinishedAt;
    private ParameterMap parameterMap;

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

    public void setParameterMap(ParameterMap parameterMaps) {
        this.parameterMap = parameterMaps;
    }

    public ParameterMap getParameterMap() {
        return parameterMap;
    }

    //ToDo Hier wird der komplette Request gebaut, momentan ohne ParameterMap
    public String buildCompleteMessage() {
        return message;
    }
}
