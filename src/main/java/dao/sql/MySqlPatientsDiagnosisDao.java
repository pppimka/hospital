package dao.sql;

import dao.PatientDiagnosisDao;
import model.PatientsDiagnosisEntity;
import org.apache.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ann_ on 02.02.15.
 */
public class MySqlPatientsDiagnosisDao implements PatientDiagnosisDao {

    public static final Logger logger = Logger.getLogger(MySqlPatientsDiagnosisDao.class);

    private Connection connection;

    @Override
    public PatientsDiagnosisEntity create(ResultSet rs) {
        PatientsDiagnosisEntity pi = new PatientsDiagnosisEntity();
        try {
            pi.setIdPatient(rs.getInt("ID_Patient"));
            pi.setIdDoctor(rs.getInt("ID_Doctor"));
            pi.setIdDiagnosis(rs.getInt("ID_Diagnosis"));
            pi.setDate(rs.getDate("Date"));
            pi.setInitialPrescription(rs.getInt("Initial_Prescription"));
            pi.setCurrentPrescription(rs.getInt("Current_Prescription"));
            pi.setIdReception(rs.getInt("ID_Registration"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        logger.debug(pi);
        return pi;
    }

    @Override
    public List<PatientsDiagnosisEntity> getByIdPatient(int key) {
        String sql = Queries.GET_ALL_PATIENTS_DIAGNOSIS_BY_ID_PATIENT;
        return fillList(sql, key);
    }

    @Override
    public List<PatientsDiagnosisEntity> getByIdDoctor(int key) {
        String sql = Queries.GET_ALL_PATIENTS_DIAGNOSIS_BY_DOCTOR_ID;
        return fillList(sql, key);
    }

    @Override
    public List<PatientsDiagnosisEntity> getForNurse() {
        String sql = Queries.GET_PATIENT_DIAGNOSIS_FOR_NURSE;
        return fillList(sql, null);
    }

    @Override
    public boolean update(PatientsDiagnosisEntity object) {
        String sql = Queries.UPDATE_PATIENT_DIAGNOSIS;
        connection = ConnectionPool.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(4, object.getIdPatient());
            statement.setInt(5, object.getIdDoctor());
            statement.setInt(6, object.getIdDiagnosis());
            statement.setDate(1, object.getDate());
            statement.setInt(2, object.getInitialPrescription());
            statement.setInt(3, object.getCurrentPrescription());
            if (statement.executeUpdate() == 1) {
                logger.debug("update successful");
                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.debug("update failed");
        return false;
    }

    @Override
    public boolean add(PatientsDiagnosisEntity object, Connection connection) {
        String sql = Queries.ADD_NEW_PATIENT_DIAGNOSIS;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(4, object.getIdPatient());
            statement.setInt(5, object.getIdDoctor());
            statement.setInt(6, object.getIdDiagnosis());
            statement.setDate(1, object.getDate());
            statement.setInt(2, object.getInitialPrescription());
            statement.setInt(3, object.getCurrentPrescription());
            statement.setInt(7, object.getIdReception());
            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.debug("add failed");
        return false;
    }

    @Override
    public void delete(PatientsDiagnosisEntity object, Connection connection) {
        this.connection = connection;
        String sql = Queries.DELETE_PATIENTS_DIAGNOSIS;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, object.getIdReception());
            statement.executeUpdate();
            logger.debug("delete successful");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<PatientsDiagnosisEntity> getAll() {
        String sql = Queries.GET_ALL_PATIENTS_DIAGNOSIS;
        return fillList(sql, null);
    }

    private List<PatientsDiagnosisEntity> fillList(String query, Integer key) {
        connection = ConnectionPool.getConnection();
        PreparedStatement statement;
        List<PatientsDiagnosisEntity> list = new ArrayList<PatientsDiagnosisEntity>();
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            if (key != null) {
                statement.setInt(1, key);
            }
            rs = statement.executeQuery();
            while (rs.next()) {
                PatientsDiagnosisEntity pi = create(rs);
                list.add(pi);
            }
            logger.debug(list);
            return list;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
            closeResultSet(rs);
        }
    }

    /**
     * Close connection
     */
    private void closeConnection() {
        if (connection != null) {
            try {
                logger.debug("close connection");
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                logger.debug("close result set");
                rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
