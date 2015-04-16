package command.logic;

import model.*;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Created by ann_ on 23.02.15.
 */
public class Wrapper implements Serializable {

    public static final Logger logger = Logger.getLogger(PatientInfoLogic.class);

    private PatientInfoEntity patientInfoEntity;
    private StaffInfoEntity staffInfoEntity;
    private PatientsDiagnosisEntity patientsDiagnosisEntity;
    private DiagnosisInfoEntity diagnosisInfoEntity;
    private PrescriptionEntity initialPrescription;
    private PrescriptionEntity currentPrescription;
    private ReceptionEntity receptionEntity;

    public Wrapper() {
    }

    public PatientInfoEntity getPatientInfoEntity() {
        logger.debug(patientInfoEntity);
        return patientInfoEntity;
    }

    public void setPatientInfoEntity(PatientInfoEntity patientInfoEntity) {
        this.patientInfoEntity = patientInfoEntity;
        logger.debug(patientInfoEntity);

    }

    public DiagnosisInfoEntity getDiagnosisInfoEntity() {
        logger.debug(diagnosisInfoEntity);
        return diagnosisInfoEntity;
    }

    public void setDiagnosisInfoEntity(DiagnosisInfoEntity diagnosisInfoEntity) {
        this.diagnosisInfoEntity = diagnosisInfoEntity;
        logger.debug(diagnosisInfoEntity);
    }

    public StaffInfoEntity getStaffInfoEntity() {
        logger.debug(staffInfoEntity);
        return staffInfoEntity;
    }

    public void setStaffInfoEntity(StaffInfoEntity staffInfoEntity) {
        logger.debug(staffInfoEntity);
        this.staffInfoEntity = staffInfoEntity;
    }

    public PatientsDiagnosisEntity getPatientsDiagnosisEntity() {
        logger.debug(patientsDiagnosisEntity);
        return patientsDiagnosisEntity;
    }

    public void setPatientsDiagnosisEntity(PatientsDiagnosisEntity patientsDiagnosisEntity) {
        logger.debug(patientsDiagnosisEntity);
        this.patientsDiagnosisEntity = patientsDiagnosisEntity;
    }

    public PrescriptionEntity getInitialPrescription() {
        logger.debug(initialPrescription);
        return initialPrescription;
    }

    public void setInitialPrescription(PrescriptionEntity initialPrescription) {
        logger.debug(initialPrescription);
        this.initialPrescription = initialPrescription;
    }

    public ReceptionEntity getReceptionEntity() {
        logger.debug(receptionEntity);
        return receptionEntity;
    }

    public void setReceptionEntity(ReceptionEntity receptionEntity) {
        logger.debug(receptionEntity);
        this.receptionEntity = receptionEntity;
    }

    public PrescriptionEntity getCurrentPrescription() {
        logger.debug(currentPrescription);
        return currentPrescription;
    }

    public void setCurrentPrescription(PrescriptionEntity currentPrescription) {
        logger.debug(currentPrescription);
        this.currentPrescription = currentPrescription;
    }
}
