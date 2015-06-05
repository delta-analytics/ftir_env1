package deltaanalytics.ftir.hardware.bruker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandProcessor {
    private OpusHttpCaller opusHttpCaller;

    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void run() {
        for (Command command : commandList) {
            opusHttpCaller.run(command.buildCompleteMessage());
        }
    }

    @Autowired
    public void setOpusHttpCaller(OpusHttpCaller opusHttpCaller) {
        this.opusHttpCaller = opusHttpCaller;
    }
}
