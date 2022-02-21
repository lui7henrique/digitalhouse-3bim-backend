import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person {
    // Attributes
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private List<String> colection = new ArrayList<String>();


     // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getColection() {
        return colection;
    }

    public void setColection(List<String> colection) {
        this.colection = colection;
    }
}
