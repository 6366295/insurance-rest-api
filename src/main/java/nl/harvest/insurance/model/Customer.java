package nl.harvest.insurance.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity(name = "CUSTOMERS")
public class Customer {

    private int id;
    private String dateOfBirth;
    private String surname;
    private String initials;
    private String nationality;
    private String bsn;
    private String iban;
    private String zipCode;
    private String houseNumber;
    private String streetName;
    private String city;
    private String telephoneNumber;
    private String email;

    private transient Set<Application> application;

    public Customer() {
    }

    public Customer(String surname, String initials) {
        this.surname = surname;
        this.initials = initials;
    }

    @OneToMany(mappedBy="customer")
    public Set<Application> getApplication() {
        return application;
    }

    public void setApplication(Set<Application> application) {
        this.application = application;
    }

    public void addApplication(Application application) {
        this.application.add(application);
    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // TODO: Properly parse Date
    @Column(name = "date_of_birth")
    // @NotBlank
    // @Temporal(TemporalType.DATE)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "surname")
    @NotBlank
    @Pattern(regexp="[a-zA-Z]+")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "initials")
    @NotBlank
    @Pattern(regexp="[a-zA-Z]+")
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Column(name = "nationality")
    // @NotBlank
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "bsn")
    // @NotBlank
    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    @Column(name = "iban")
    // @NotBlank
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Column(name = "zip_code")
    // @NotBlank
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Column(name = "house_number")
    // @NotBlank
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Column(name = "street_name")
    // @NotBlank
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(name = "city")
    // @NotBlank
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "telephone_number")
    // @NotBlank
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Column(name = "email")
    // @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
