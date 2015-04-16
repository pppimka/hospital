package command.logic;

import com.mysql.jdbc.exceptions.MySQLDataException;
import dao.*;
import model.*;
import org.apache.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ann_ on 25.02.15.
 */
public class SetDiagnosisLogic {
    public static final Logger logger = Logger.getLogger(SetDiagnosisLogic.class);

    private PatientDao patientDao;
    private PatientDiagnosisDao patientDiagnosisDao;
    private DiagnosisInfoDao diagnosisInfoDao;
    private PrescriptionDao prescriptionDao;
    private ReceptionDao receptionDao;

    private List<PatientInfoEntity> patient;
    private StaffInfoEntity staffInfo;

    public SetDiagnosisLogic(DaoFactory daoFactory, StaffInfoEntity staffInfo) {
        this.staffInfo = staffInfo;
        this.patientDao = daoFactory.getPatientDao();
        this.patientDiagnosisDao = daoFactory.getPatientDiagnosisDao();
        this.diagnosisInfoDao = daoFactory.getDiagnosisInfoDao();
        this.prescriptionDao = daoFactory.getPrescriptionDao();
        this.receptionDao = daoFactory.getReceptionDao();
    }

    public Map<Integer,PatientInfoEntity> getAllPatientInfo() {
        patient = patientDao.getAll();
        Map<Integer,PatientInfoEntity> map = new HashMap<Integer, PatientInfoEntity>();

        for(PatientInfoEntity p : patient) {
            map.put(p.getIdPatient(),p);
        }
        logger.debug(map);
        return map;
    }

    public Map<String,Wrapper> getAllHealthyPatientInfo() {
        patient = patientDao.getAll();
        List<PatientsDiagnosisEntity> patientsDiagnosisEntities = patientDiagnosisDao.getAll();
        Map<String,Wrapper> map = new HashMap<String, Wrapper>();
        for(PatientInfoEntity p : patient) {
            for (PatientsDiagnosisEntity pd: patientsDiagnosisEntities){
                if (pd.getIdPatient().equals(p.getIdPatient()) && pd.getIdDoctor().equals(staffInfo.getIdStaff())
                && pd.getCurrentPrescription().equals(1)) {
                    Wrapper wrapper = new Wrapper();
                    wrapper.setPatientsDiagnosisEntity(pd);
                    wrapper.setPatientInfoEntity(p);
                    map.put(pd.getIdReception().toString(), wrapper);
                }
            }

        }
        logger.debug(map);
        return map;
    }

    public Map<Integer,DiagnosisInfoEntity> getAllDiagnosis() {
        List<DiagnosisInfoEntity> diagnosisInfo = diagnosisInfoDao.getAll();
        Map<Integer,DiagnosisInfoEntity> map = new HashMap<Integer, DiagnosisInfoEntity>();
        for(DiagnosisInfoEntity p : diagnosisInfo) {
            map.put(p.getIdDiagnosis(),p);
        }
        logger.debug(map);
        return map;
    }

    public Map<Integer,PrescriptionEntity> getAllPrescriptions() {
        List<PrescriptionEntity> prescriptionEntities = prescriptionDao.getAll();
        Map<Integer,PrescriptionEntity> map = new HashMap<Integer, PrescriptionEntity>();
        for(PrescriptionEntity p : prescriptionEntities) {
            map.put(p.getIdPrescription(),p);
        }
        logger.debug(map);
        return map;
    }

    public boolean setDiagnosis(Integer idPatient, Integer idPrescription, Integer idDiagnosisInfo) {
        Connection conn = ConnectionPool.getConnection();
        PatientInfoEntity patient = patientDao.getByPK(idPatient);
        DiagnosisInfoEntity diagnosisInfo = diagnosisInfoDao.getByPK(idDiagnosisInfo);
        PrescriptionEntity prescription = prescriptionDao.getByPK(idPrescription);
        boolean setDiagnosis = false;
        PatientsDiagnosisEntity patientsDiagnosisEntity = new PatientsDiagnosisEntity();
        patientsDiagnosisEntity.setIdPatient(patient.getIdPatient());
        patientsDiagnosisEntity.setIdDoctor(staffInfo.getIdStaff());
        patientsDiagnosisEntity.setIdDiagnosis(diagnosisInfo.getIdDiagnosis());
        patientsDiagnosisEntity.setDate(Date.valueOf(LocalDate.now()));
        patientsDiagnosisEntity.setInitialPrescription(prescription.getIdPrescription());
        patientsDiagnosisEntity.setCurrentPrescription(prescription.getIdPrescription());

        ReceptionEntity receptionEntity = new ReceptionEntity();
        receptionEntity.setIdPatient(patient.getIdPatient());
        receptionEntity.setIdDoctor(staffInfo.getIdStaff());
        receptionEntity.setDateOfAdmission(Date.valueOf(LocalDate.now()));
        try {
            conn.setAutoCommit(false);
            int i;
            i = receptionDao.add(receptionEntity, conn);

            if (i == 0 ){
                throw new MySQLDataException();
            }
            patientsDiagnosisEntity.setIdReception(i);
            setDiagnosis = patientDiagnosisDao.add(patientsDiagnosisEntity, conn);

            conn.commit();
            return setDiagnosis;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.debug(setDiagnosis);
        return setDiagnosis;
    }
}
