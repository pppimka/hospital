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
public class StaffLogic {

    public static final Logger logger = Logger.getLogger(PatientInfoLogic.class);

    private PatientDao patientDao;
    private PatientDiagnosisDao patientDiagnosisDao;
    private DiagnosisInfoDao diagnosisInfoDao;
    private PrescriptionDao prescriptionDao;

    private List<PatientsDiagnosisEntity> diagnosisList;
    private StaffInfoEntity staffInfo;

    public StaffLogic(DaoFactory daoFactory, StaffInfoEntity staffInfoEntity) {
        this.patientDiagnosisDao = daoFactory.getPatientDiagnosisDao();
        this.diagnosisInfoDao = daoFactory.getDiagnosisInfoDao();
        this.staffInfo = staffInfoEntity;
        this.prescriptionDao = daoFactory.getPrescriptionDao();
        this.patientDao = daoFactory.getPatientDao();
    }

    public Map<String,Wrapper> getAllPatientsInfoForDoctor() {
        diagnosisList = patientDiagnosisDao.getByIdDoctor(staffInfo.getIdStaff());
        logger.debug(diagnosisList);
        return fillMap(diagnosisList);
    }

    public Map<String,Wrapper> getPatientsInfoForNurse() {
        diagnosisList = patientDiagnosisDao.getForNurse();
        logger.debug(diagnosisList);
        return fillMap(diagnosisList);
    }

    private Map<String, Wrapper> fillMap(List<PatientsDiagnosisEntity> diagnosisList) {
        Map<String,Wrapper> list = new HashMap<String, Wrapper>();
        Wrapper wrapper;
        if (diagnosisList == null) {
            return list;
        }
        for (int i = 0; i < diagnosisList.size(); i++) {
            wrapper = new Wrapper();
            PatientInfoEntity patient = patientDao.getByPK(diagnosisList.get(i).getIdPatient());
            wrapper.setPatientInfoEntity(patient);

            DiagnosisInfoEntity diagnosisInfo = diagnosisInfoDao.getByPK(diagnosisList.get(i).getIdDiagnosis());
            wrapper.setDiagnosisInfoEntity(diagnosisInfo);

            PrescriptionEntity prescription = prescriptionDao.getByPK(diagnosisList.get(i).getInitialPrescription());
            wrapper.setInitialPrescription(prescription);

            prescription = prescriptionDao.getByPK(diagnosisList.get(i).getCurrentPrescription());
            wrapper.setCurrentPrescription(prescription);

            wrapper.setPatientsDiagnosisEntity(diagnosisList.get(i));
            list.put(patient.getIdPatient().toString() + diagnosisList.get(i).getIdDoctor()
                    + diagnosisInfo.getIdDiagnosis(), wrapper);

        }
        logger.debug(list);
        return list;
    }
}
