package deltaanalytics.ftir.hardware.bruker;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandProcessorTest {
    @Test
    public void runCommands(){
        Command command1 = new Command();
        command1.setMessage("runMeasurement");
        Command command2 = new Command();
        command2.setMessage("writeResultFile");
        OpusHttpCaller opusHttpCaller = mock(OpusHttpCaller.class);
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.addCommand(command1);
        commandProcessor.addCommand(command2);
        commandProcessor.setOpusHttpCaller(opusHttpCaller);

        commandProcessor.run();

        verify(opusHttpCaller).run(command1.buildCompleteMessage());
        verify(opusHttpCaller).run(command2.buildCompleteMessage());
    }
}
