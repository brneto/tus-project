package ie.ait.agile.agileproject.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(length = 1000)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
