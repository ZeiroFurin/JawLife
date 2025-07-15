package JawLife;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JawLifeModel { private final StringProperty clientName;
    private final StringProperty program;
    private final StringProperty lastActivity;
    private final StringProperty status;

    public JawLifeModel(String clientName, String program, String lastActivity, String status) {
        this.clientName = new SimpleStringProperty(clientName);
        this.program = new SimpleStringProperty(program);
        this.lastActivity = new SimpleStringProperty(lastActivity);
        this.status = new SimpleStringProperty(status);
    }

    // --- Getters and Property Methods ---

    public String getClientName() { return clientName.get(); }
    public StringProperty clientNameProperty() { return clientName; }

    public String getProgram() { return program.get(); }
    public StringProperty programProperty() { return program; }

    public String getLastActivity() { return lastActivity.get(); }
    public StringProperty lastActivityProperty() { return lastActivity; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
}
