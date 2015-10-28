package deltaanalytics.ftir.hardware.jueke.controller;

import deltaanalytics.ftir.hardware.jueke.model.JuekeConfigurationParameter;
import deltaanalytics.ftir.hardware.jueke.model.JuekeConfigurationParameterRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author frank
 */
@Service
@Transactional
public class JuekeConfigurationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeConfigurationService.class);

    @Autowired
    private JuekeConfigurationParameterRepository juekeConfigurationParameterRepository;

    public void initializeJuekeConfiguration() {
        LOGGER.info("initializeJuekeConfiguration");
        try {
            LOGGER.info("Load src/main/resources/jueke_status_parameter_en.properties");
            loadJuekeConfigurationParameter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<JuekeConfigurationParameter> readAll() {
        initializeJuekeConfiguration();
        List<JuekeConfigurationParameter> juekeConfigurationParameters = juekeConfigurationParameterRepository.findAll();
        LOGGER.info("juekeConfigurationParameters in DB " + juekeConfigurationParameters.size());
        return juekeConfigurationParameters;
    }

    private void loadJuekeConfigurationParameter() throws IOException {
        Properties juekeConfigurationParameterProperties = getProperties("src/main/resources/jueke_status_parameter_en.properties");
        for (Map.Entry<Object, Object> keyAndValue : juekeConfigurationParameterProperties.entrySet()) {
            JuekeConfigurationParameter juekeConfigurationParameter = new JuekeConfigurationParameter((String) keyAndValue.getKey(), (String) keyAndValue.getValue());
            juekeConfigurationParameterRepository.save(juekeConfigurationParameter);
        }
    }

    private Properties getProperties(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(name));
        new FileInputStream(name).close();
        return properties;
    }    
    
      
}
