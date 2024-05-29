package trade.kuharchuk.cryptocompare.response;

import trade.kuharchuk.cryptocompare.domain.InstrumentStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentMetadataResponse {
  @JsonAlias("METADATA_VERSION")
  private int version;

  @JsonAlias("INSTRUMENT_STATUS")
  private InstrumentStatus status;

  @JsonAlias("FIRST_SEEN_ON_INDEX_COMPOSITION_TS")
  private Long firstSeenInIndex;

  @JsonAlias("LAST_SEEN_ON_INDEX_COMPOSITION_TS")
  private Long lastSeenInIndex;

  @JsonAlias("INSTRUMENT")
  private String instrument;

  @JsonAlias("INSTRUMENT_MAPPING")
  private Map<String, Object> instrumentMapping;

  @JsonAlias("INSTRUMENT_EXTERNAL_DATA")
  private Map<String, Object> instrumentExternalData;

  @JsonAlias("ARCHIVE_STATUS")
  private String archiveStatus;

  @JsonAlias("HOST_MIGRATION_STATUS")
  private String hostMigrationStatus;

  @JsonAlias("HOST_MIGRATION_SOURCE")
  private String hostMigrationSource;

  @JsonAlias("HOST_MIGRATION_DESTINATION")
  private String hostMigrationDestination;

  @JsonAlias("OUTLIER_EXCLUSION_PERCENTAGE")
  private Double outlierExclusionPercentage;

  @JsonAlias("COMPONENTS")
  private List<String> components;

  @JsonAlias("OLDEST_HISTORICAL_DAY_DATA_TIMESTAMP")
  private Long oldestHistoricalDayDataTimestamp;

  @JsonAlias("TOTAL_INDEX_UPDATES")
  private Long totalIndexUpdates;

  @JsonAlias("FIRST_INDEX_UPDATE_FROM_CALCULATED")
  private Map<String, Object> firstIndexUpdateFromCalculated;

  @JsonAlias("LAST_INDEX_UPDATE_FROM_CALCULATED")
  private Map<String, Object> lastIndexUpdateFromCalculated;
}
