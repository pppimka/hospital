package dao.sql;

import dao.*;
import org.apache.log4j.Logger;

/**
 * Created by ann_ on 02.02.15.
 */
public class MySqlDaoFactory implements DaoFactory {

    public static final Logger logger = Logger.getLogger(MySqlDaoFactory.class);

    @Override
    public PatientDao getPatientDao() {
        logger.debug("patient dao");
        return new MySqlPatientInfoDao();
    }

    @Override
    public StaffDao getStaffDao() {
        logger.debug("staff dao");
        return new MySqlStaffInfoDao();
    }

    @Override
    public PatientDiagnosisDao getPatientDiagnosisDao() {
        logger.debug("patient diagnosis dao");
        return new MySqlPatientsDiagnosisDao();
    }

    @Override
    public DiagnosisInfoDao getDiagnosisInfoDao() {
        logger.debug("diagnosis info dao");
        return new MySqlDiagnosisInfoDao();
    }

    @Override
    public PrescriptionDao getPrescriptionDao() {
        logger.debug("prescription info dao");
        return new MySqlPrescriptionDao();
    }

    @Override
    public ReceptionDao getReceptionDao() {
        logger.debug("reception dao");
        return new MySqlReceptionDao();
    }
}
