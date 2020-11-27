package BigProject;

import java.io.Serializable;

public class PackageDataAircraft implements Serializable {
    private String operationType;
    private String AircraftLabel;

    public PackageDataAircraft(String operationType, String aircraftLabel) {
        this.operationType = operationType;
        AircraftLabel = aircraftLabel;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getAircraftLabel() {
        return AircraftLabel;
    }

    public void setAircraftLabel(String aircraftLabel) {
        AircraftLabel = aircraftLabel;
    }
}
