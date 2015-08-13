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
    private MeasurementCommandProcessor measurementCommandProcessor;
    private ReferenceMeasurementCommandProcessor referenceMeasurementCommandProcessor;

    //Beispiel: http://localhost/OpusCommand.htm?MeasureReference (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    // Response 'OK 0' oder wenn ein Fehler auftrat ein Fehler-code
    public void runReferenceMeasurement(String exp, String xpp) {
        LOGGER.info("runReferenceMeasurement");
        Command command = new Command();
        command.setMessage("http://localhost/OpusCommand.htm?MeasureReference");
        command.getBrukerConfigurationParameterList().add(buildFrom("EXP", exp));
        command.getBrukerConfigurationParameterList().add(buildFrom("XPP", xpp));
        referenceMeasurementCommandProcessor.run(command);
    }

    //Beispiel: http://localhost/OpusCommand.htm?MeasureSample (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    //Response ist z.B. 'OK 1 "C:\OPUS_7.0.129\MEAS3\Test24.1" 1 654 0', daraus ist die file ID 654 und der Pfad+Dateiname zu entnehmen um abzuspeichern
    public void runMeasurement(String exp, String xpp) {
        LOGGER.info("runMeasurement");
        Command command = new Command();
        command.setMessage("http://localhost/OpusCommand.htm?MeasureSample");
        command.getBrukerConfigurationParameterList().add(buildFrom("EXP", exp));
        command.getBrukerConfigurationParameterList().add(buildFrom("XPP", xpp));
        measurementCommandProcessor.run(command);
    }

    private BrukerConfigurationParameter buildFrom(String key, String value) {
        BrukerConfigurationParameter brukerConfigurationParameter = new BrukerConfigurationParameter();
        brukerConfigurationParameter.setKey(key);
        brukerConfigurationParameter.setValue(value);
        return brukerConfigurationParameter;
    }

    @Autowired
    private void setMeasurementCommandProcessor(MeasurementCommandProcessor measurementCommandProcessor) {
        this.measurementCommandProcessor = measurementCommandProcessor;
    }

    @Autowired
    public void setReferenceMeasurementCommandProcessor(ReferenceMeasurementCommandProcessor referenceMeasurementCommandProcessor) {
        this.referenceMeasurementCommandProcessor = referenceMeasurementCommandProcessor;
    }
}
