package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import deltaanalytics.ftir.hardware.bruker.model.MeasurementResponse;
import deltaanalytics.ftir.hardware.bruker.model.ReferenceMeasurementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dk.ange.octave.OctaveEngine;

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
        ReferenceMeasurementResponse referenceMeasurementResponse = new ReferenceMeasurementResponse(command.getResponse());
        if (referenceMeasurementResponse.successfull()) {
            LOGGER.info("ReferenceMeasurement successfull finished");
        } else {
            LOGGER.error("ReferenceMeasurement not successfull finished =>" + referenceMeasurementResponse.getErrorCode());
        }
    }

    //Beispiel: http://localhost/OpusCommand.htm?MeasureSample (0, {EXP='frank.xpm', XPP='C:\OPUS_7.0.129\XPM'})
    //Response ist z.B. 'OK 1 "C:\OPUS_7.0.129\MEAS3\Test24.1" 1 654 0', daraus ist die file ID 654 und der Pfad+Dateiname zu entnehmen um abzuspeichern
    //SaveAs http://localhost/OpusCommand.htm?SaveAs ([654:AB], {DAP='C:\OPUS_7.0.129\MEAS3', OEX='0', SAN='Test24.1.dpt'})
    public void runMeasurement(String exp, String xpp) {
        LOGGER.info("runMeasurement");
        Command command = new Command();
        command.setMessage("http://localhost/OpusCommand.htm?MeasureSample");
        command.getBrukerConfigurationParameterList().add(buildFrom("EXP", exp));
        command.getBrukerConfigurationParameterList().add(buildFrom("XPP", xpp));
        measurementCommandProcessor.run(command);
        MeasurementResponse measurementResponse = new MeasurementResponse(command.getResponse());
        if (command.getException() == null) {
            Command saveCommand = new Command();
            command.setMessage("http://localhost/OpusCommand.htm?SaveAs");
            command.getBrukerConfigurationParameterList().add(buildFrom("DAP", measurementResponse.getDAP()));
            command.getBrukerConfigurationParameterList().add(buildFrom("SAN", measurementResponse.getSAN()));
            measurementCommandProcessor.run(measurementResponse.getFileId(), saveCommand);
        } else {
            LOGGER.error("Measurement Exception, no saveAs Command allowed");
        }
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
