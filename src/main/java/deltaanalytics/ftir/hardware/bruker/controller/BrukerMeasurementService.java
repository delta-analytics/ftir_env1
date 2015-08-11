package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BrukerMeasurementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrukerMeasurementService.class);
    private CommandProcessor commandProcessor;

    //Beispiel: http://localhost/OpusCommand.htm?MeasureReference (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    public void runReferenceMeasurement(String exp, String xpp) {
        LOGGER.info("runReferenceMeasurement");
        Command command = new Command();
        command.setMessage("http://localhost/OpusCommand.htm?MeasureReference");
        command.getBrukerConfigurationParameterList().add(buildFrom("EXP", exp));
        command.getBrukerConfigurationParameterList().add(buildFrom("XPP", xpp));
        commandProcessor.addCommand(command);
        commandProcessor.run();
    }

    //Beispiel: http://localhost/OpusCommand.htm?MeasureSample (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    public void runMeasurement(String exp, String xpp) {
        LOGGER.info("runMeasurement");
        Command command = new Command();
        command.setMessage("http://localhost/OpusCommand.htm?MeasureSample");
        command.getBrukerConfigurationParameterList().add(buildFrom("EXP", exp));
        command.getBrukerConfigurationParameterList().add(buildFrom("XPP", xpp));
        commandProcessor.addCommand(command);
        commandProcessor.run();
    }

    private BrukerConfigurationParameter buildFrom(String key, String value){
        BrukerConfigurationParameter brukerConfigurationParameter = new BrukerConfigurationParameter();
        brukerConfigurationParameter.setKey(key);
        brukerConfigurationParameter.setValue(value);
        return brukerConfigurationParameter;
    }

    @Autowired
    private void setCommandProcessor(CommandProcessor commandProcessor){
        this.commandProcessor = commandProcessor;
    }
}
