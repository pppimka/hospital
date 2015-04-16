package model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ann_ on 12.02.15.
 */

public class PatientsDiagnosisEntity implements Serializable{

    public static final Logger logger = Logger.getLogger(PatientsDiagnosisEntity.class);

    private Integer idPatient;
    private Integer idDoctor;
    private Integer idDiagnosis;
    private Date date;
    private Integer initialPrescription;
    private Integer currentPrescription;
    private Integer idReception;

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

    public Integer getIdDiagnosis() {
        logger.debug(idDiagnosis);
        return idDiagnosis;
    }

    public void setIdDiagnosis(Integer idDiagnosis) {
        logger.debug(idDiagnosis);
        this.idDiagnosis = idDiagnosis;
    }

    public Date getDate() {
        logger.debug(date);
        return date;
    }

    public void setDate(Date date) {
        logger.debug(date);
        this.date = date;
    }

    public Integer getInitialPrescription() {
        logger.debug(initialPrescription);
        return initialPrescription;
    }

    public void setInitialPrescription(Integer initialPrescription) {
        logger.debug(initialPrescription);
        this.initialPrescription = initialPrescription;
    }

    public Integer getCurrentPrescription() {
        logger.debug(currentPrescription);
        return currentPrescription;
    }

    public void setCurrentPrescription(Integer currentPrescription) {
        logger.debug(currentPrescription);
        this.currentPrescription = currentPrescription;
    }

    public Integer getIdReception() {
        logger.debug(idReception);
        return idReception;
    }

    public void setIdReception(Integer idReception) {
        logger.debug(idReception);
        this.idReception = idReception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientsDiagnosisEntity that = (PatientsDiagnosisEntity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (idDiagnosis != null ? !idDiagnosis.equals(that.idDiagnosis) : that.idDiagnosis != null) return false;
        if (idDoctor != null ? !idDoctor.equals(that.idDoctor) : that.idDoctor != null) return false;
        if (idPatient != null ? !idPatient.equals(that.idPatient) : that.idPatient != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPatient != null ? idPatient.hashCode() : 0;
        result = 31 * result + (idDoctor != null ? idDoctor.hashCode() : 0);
        result = 31 * result + (idDiagnosis != null ? idDiagnosis.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

}
