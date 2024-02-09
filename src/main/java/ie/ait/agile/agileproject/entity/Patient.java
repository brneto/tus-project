package ie.ait.agile.agileproject.entity;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String username;

    private String password;


    private String name;


    private String email;

    private Long emergencyId;

    private boolean active;

    @OneToOne(cascade=CascadeType.ALL)
    private Gp gp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Prescription> prescriptionList;

    public Gp getGp() {
        return gp;
    }

    public void setGp(Gp gp) {
        this.gp = gp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(Long emergencyId) {
        this.emergencyId = emergencyId;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
