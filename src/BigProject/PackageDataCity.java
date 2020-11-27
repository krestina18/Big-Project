package BigProject;

import java.io.Serializable;

public class PackageDataCity implements Serializable {
    private String operationType;
    private String cityLabel;

    public PackageDataCity(String operationType, String cityLabel) {
        this.operationType = operationType;
        this.cityLabel = cityLabel;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }
}
