package JawLife;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ClientModel {
    private final StringProperty name;
    private final StringProperty Gander;
    private final StringProperty program;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty address;
    private final StringProperty goalClass;
    private final StringProperty startDate;
    private final StringProperty endDate;

    public ClientModel(String name, String Gander, String program, String email, String phone, String address, String goalclass, String startDate, String endDate) {
        this.name = new SimpleStringProperty(name);
        this.Gander = new SimpleStringProperty(Gander);
        this.program = new SimpleStringProperty(program);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.goalClass = new SimpleStringProperty(goalclass);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
    }
    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public String getGander() { return Gander.get(); }
    public StringProperty GanderProperty() { return Gander; }
    public String getProgram() { return program.get(); }
    public StringProperty programProperty() { return program; }
    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }
    public String getPhone() { return phone.get(); }
    public StringProperty phoneProperty() { return phone; }
    public String getAddress() { return address.get(); }
    public String getGoal() { return goalClass.get(); }
    public StringProperty goalClassProperty() {return goalClass; }
    public String getStartDate() { return startDate.get(); }
    public String getEndDate() { return endDate.get(); }

    @Override
    public String toString() {
        return getName();
    }
}
