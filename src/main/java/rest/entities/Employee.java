package rest.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
    @Id
    @Column(name = "ssn", nullable = false)
    private String ssn;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "working", nullable = false)
    private Boolean working;

    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }
}