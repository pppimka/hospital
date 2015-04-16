package model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ann_ on 12.02.15.
 */

public class ReceptionEntity implements Serializable {

    public static final Logger logger = Logger.getLogger(PatientInfoEntity.class);

    private Integer idRegistration;
    private Integer idPatient;
    private Integer idDoctor;
    private Integer idFinalDiagnosis;
    private Date dateOfAdmission;
    private Date dateOfDischarge;


    public Integer getIdRegistration() {
        logger.debug(idRegistration);
        return idRegistration;
    }

    public void setIdRegistration(Integer idRegistration) {
        logger.debug(idRegistration);
        this.idRegistration = idRegistration;
    }

    public Integer getIdPatient() {
        logger.debug(idPatient);
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        logger.debug(idPatient);
        this.idPatient = idPatient;
    }

    public Integer getIdDoctor() {
        logger.debug(idDoctor);
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        logger.debug(idDoctor);
        this.idDoctor = idDoctor;
    }

    public Integer getIdFinalDiagnosis() {
        logger.debug(idFinalDiagnosis);
        return idFinalDiagnosis;
    }

    public void setIdFinalDiagnosis(Integer idFinalDiagnosis) {
        logger.debug(idFinalDiagnosis);
        this.idFinalDiagnosis = idFinalDiagnosis;
    }

    public Date getDateOfAdmission() {
        logger.debug(dateOfAdmission);
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        logger.debug(dateOfAdmission);
        this.dateOfAdmission = dateOfAdmission;
    }

    public Date getDateOfDischarge() {
        logger.debug(dateOfDischarge);
        return dateOfDischarge;
    }

    public void setDateOfDischarge(Date dateOfDischarge) {
        logger.debug(dateOfDischarge);
        this.dateOfDischarge = dateOfDischarge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceptionEntity that = (ReceptionEntity) o;

        if (dateOfAdmission != null ? !dateOfAdmission.equals(that.dateOfAdmission) : that.dateOfAdmission != null)
            return false;
        if (dateOfDischarge != null ? !dateOfDischarge.equals(that.dateOfDischarge) : that.dateOfDischarge != null)
            return false;
        if (idFinalDiagnosis != null ? !idFinalDiagnosis.equals(that.idFinalDiagnosis) : that.idFinalDiagnosis != null)
            return false;
        if (idRegistration != null ? !idRegistration.equals(that.idRegistration) : that.idRegistration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegistration != null ? idRegistration.hashCode() : 0;
        result = 31 * result + (idFinalDiagnosis != null ? idFinalDiagnosis.hashCode() : 0);
        result = 31 * result + (dateOfAdmission != null ? dateOfAdmission.hashCode() : 0);
        result = 31 * result + (dateOfDischarge != null ? dateOfDischarge.hashCode() : 0);
        return result;
    }

}
