package deltaanalytics.ftir.hardware.bruker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandProcessor.class);
    private OpusHttpCaller opusHttpCaller;

    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void run() {
        for (Command command : commandList) {
            command.setExecutionStartedAt(LocalDateTime.now());
            String completeMessage = command.buildCompleteMessage();
            LOGGER.info("run " + completeMessage);
            opusHttpCaller.run(completeMessage);
            command.setExecutionFinishedAt(LocalDateTime.now());
        }
    }

    @Autowired
    public void setOpusHttpCaller(OpusHttpCaller opusHttpCaller) {
        this.opusHttpCaller = opusHttpCaller;
    }
}
