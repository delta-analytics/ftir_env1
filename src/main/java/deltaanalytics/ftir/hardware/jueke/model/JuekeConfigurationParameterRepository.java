package deltaanalytics.ftir.hardware.jueke.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuekeConfigurationParameterRepository extends JpaRepository<JuekeConfigurationParameter, Long> {
    JuekeConfigurationParameter findByKey(String key);
}
