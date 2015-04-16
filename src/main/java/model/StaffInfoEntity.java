package model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ann_ on 12.02.15.
 */
@Entity
@Table(name = "staff_info", schema = "", catalog = "hospital")
public class StaffInfoEntity implements Serializable{

    public static final Logger logger = Logger.getLogger(PatientInfoEntity.class);

    private Integer idStaff;
    private String name;
    private String surname;
    private Date birthday;
    private String typeStaff;
    private String phoneNumber;
    private String eMail;
    private String password;

    @Id
    @Column(name = "ID_Staff")
    public Integer getIdStaff() {
        logger.debug(idStaff);
        return idStaff;
    }

    public void setIdStaff(Integer idStaff) {
        logger.debug(idStaff);
        this.idStaff = idStaff;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        logger.debug(name);
        return name;
    }

    public void setName(String name) {
        logger.debug(name);
        this.name = name;
    }

    @Basic
    @Column(name = "Surname")
    public String getSurname() {
        logger.debug(surname);
        return surname;
    }

    public void setSurname(String surname) {
        logger.debug(surname);
        this.surname = surname;
    }

    @Basic
    @Column(name = "Birthday")
    public Date getBirthday() {
        logger.debug(birthday);
        return birthday;
    }

    public void setBirthday(Date birthday) {
        logger.debug(birthday);
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "TypeStaff")
    public String getTypeStaff() {
        logger.debug(typeStaff);
        return typeStaff;
    }

    public void setTypeStaff(String typeStaff) {
        logger.debug(typeStaff);
        this.typeStaff = typeStaff;
    }

    @Basic
    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        logger.debug(phoneNumber);
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        logger.debug(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "e_mail")
    public String geteMail() {
        logger.debug(eMail);
        return eMail;
    }

    public void seteMail(String eMail) {
        logger.debug(eMail);
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        logger.debug(password);
        return password;
    }

    public void setPassword(String password) {
        logger.debug(password);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffInfoEntity that = (StaffInfoEntity) o;

        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (idStaff != null ? !idStaff.equals(that.idStaff) : that.idStaff != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (typeStaff != null ? !typeStaff.equals(that.typeStaff) : that.typeStaff != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStaff != null ? idStaff.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (typeStaff != null ? typeStaff.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
