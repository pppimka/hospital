package command.logic;

import dao.*;
import model.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ann_ on 23.02.15.
 */
public class PatientInfoLogic {
    
    public static final Logger logger = Logger.getLogger(PatientInfoLogic.class);

    private DaoFactory daoFactory;
    private PatientDiagnosisDao patientDiagnosisDao;
    private DiagnosisInfoDao diagnosisInfoDao;
    private StaffDao staffDao;
    private PrescriptionDao prescriptionDao;

    private PatientInfoEntity patient;
    private List<PatientsDiagnosisEntity> patientsDiagnosis;
    private DiagnosisInfoEntity diagnosisInfo;
    private StaffInfoEntity staffInfo;
    private PrescriptionEntity prescription;

    public PatientInfoLogic(DaoFactory factory, PatientInfoEntity patient) {
        this.daoFactory = factory;
        this.patient = patient;
        this.patientDiagnosisDao = daoFactory.getPatientDiagnosisDao();
        this.diagnosisInfoDao = daoFactory.getDiagnosisInfoDao();
        this.staffDao = daoFactory.getStaffDao();
        this.prescriptionDao = daoFactory.getPrescriptionDao();
    }

    public Map<String,Wrapper> getPatientInfo() {
        Map<String,Wrapper> map = new HashMap<String, Wrapper>();
        Wrapper wrapper = null;
        patient.setPatientsDiagnosis(patientDiagnosisDao.getByIdPatient(patient.getIdPatient()));
        patientsDiagnosis = patient.getPatientsDiagnosis();

        logger.debug(patientsDiagnosis);

        if (patientsDiagnosis == null) {
            return map;
        }
        for (int i = 0; i < patientsDiagnosis.size(); i++) {
            wrapper = new Wrapper();
            staffInfo = staffDao.getByPK(patientsDiagnosis.get(i).getIdDoctor());
            wrapper.setStaffInfoEntity(staffInfo);

            diagnosisInfo = diagnosisInfoDao.getByPK(patientsDiagnosis.get(i).getIdDiagnosis());
            wrapper.setDiagnosisInfoEntity(diagnosisInfo);

            prescription = prescriptionDao.getByPK(patientsDiagnosis.get(i).getInitialPrescription());
            wrapper.setInitialPrescription(prescription);

            prescription = prescriptionDao.getByPK(patientsDiagnosis.get(i).getCurrentPrescription());
            wrapper.setCurrentPrescription(prescription);
            map.put(patient.getIdPatient().toString(), wrapper);
        }
        return map;
    }
}
