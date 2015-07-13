package deltaanalytics.ftir.hardware.bruker.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrukerConfigurationParameterOptionRepository extends JpaRepository<BrukerConfigurationParameterOption, Long> {
    BrukerConfigurationParameter findByKey(String key);
}
