package deltaanalytics.ftir.hardware.bruker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeasurementCommandProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementCommandProcessor.class);
    private OpusHttpCaller opusHttpCaller;


    public void run(Command command) {
        command.setExecutionStartedAt(LocalDateTime.now());
        String completeMessage = command.buildCompleteMessage();
        try {
            command.setResponse(opusHttpCaller.run(completeMessage));
        } catch (Exception e) {
            command.setException(e);
        }
        command.setExecutionFinishedAt(LocalDateTime.now());

    }
    
    public void run(String fileId, Command command) {
        command.setExecutionStartedAt(LocalDateTime.now());
        String completeMessage = command.buildCompleteMessage();
        completeMessage.replaceFirst("0", "["+fileId+":AB]");
                try {
            command.setResponse(opusHttpCaller.run(completeMessage));
        } catch (Exception e) {
            command.setException(e);
        }
        command.setExecutionFinishedAt(LocalDateTime.now());

    }

    @Autowired
    public void setOpusHttpCaller(OpusHttpCaller opusHttpCaller) {
        this.opusHttpCaller = opusHttpCaller;
    }
}
