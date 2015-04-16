package model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by ann_ on 12.02.15.
 */

public class PatientInfoEntity implements Serializable {

    public static final Logger logger = Logger.getLogger(PatientInfoEntity.class);

    private Integer idPatient;
    private String name;
    private String surname;
    private Date birthday;
    private String phoneNumber;
    private String eMail;
    private String password;
    private List<PatientsDiagnosisEntity> patientsDiagnosis;

    @Id
    @Column(name = "ID_Patient")
    public Integer getIdPatient() {
        logger.debug(idPatient);
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        logger.debug(idPatient);
        this.idPatient = idPatient;
    }

    public String getName() {
        logger.debug(name);
        return name;
    }

    public void setName(String name) {
        logger.debug(name);
        this.name = name;
    }

    public String getSurname() {
        logger.debug(surname);
        return surname;
    }

    public void setSurname(String surname) {
        logger.debug(surname);
        this.surname = surname;
    }

    public Date getBirthday() {
        logger.debug(birthday);
        return birthday;
    }

    public void setBirthday(Date birthday) {
        logger.debug(birthday);
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        logger.debug(phoneNumber);
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        logger.debug(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        logger.debug(eMail);
        return eMail;
    }

    public void seteMail(String eMail) {
        logger.debug(eMail);
        this.eMail = eMail;
    }

    public String getPassword() {
        logger.debug(password);
        return password;
    }

    public void setPassword(String password) {
        logger.debug(password);
        this.password = password;
    }

    public List<PatientsDiagnosisEntity> getPatientsDiagnosis() {
        logger.debug(patientsDiagnosis);
        return patientsDiagnosis;
    }

    public void setPatientsDiagnosis(List<PatientsDiagnosisEntity> list) {
        logger.debug(patientsDiagnosis);
        patientsDiagnosis = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientInfoEntity that = (PatientInfoEntity) o;

        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (idPatient != null ? !idPatient.equals(that.idPatient) : that.idPatient != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPatient != null ? idPatient.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
