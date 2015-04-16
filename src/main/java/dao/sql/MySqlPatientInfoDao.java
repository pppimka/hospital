package dao.sql;

import dao.PatientDao;
import model.PatientInfoEntity;
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
public class MySqlPatientInfoDao implements PatientDao {
    public static final Logger logger = Logger.getLogger(MySqlPatientInfoDao.class);

    private PreparedStatement statement;
    private Connection connection;

    public MySqlPatientInfoDao() {
    }

    public boolean addNewPatient(PatientInfoEntity patient) {
        try {
            connection = ConnectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(Queries.ADD_NEW_PATIENT);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setDate(3, patient.getBirthday());
            statement.setString(4, patient.getPhoneNumber());
            statement.setString(5, patient.geteMail());
            statement.setString(6, patient.getPassword());
            int i = statement.executeUpdate();
            logger.debug(i);
            return i > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public PatientInfoEntity create(ResultSet rs) {
        PatientInfoEntity pi = new PatientInfoEntity();
        try {
            pi.setIdPatient(rs.getInt("ID_Patient"));
            pi.setName(rs.getString("Name"));
            pi.setSurname(rs.getString("Surname"));
            pi.setPhoneNumber(rs.getString("PhoneNumber"));
            pi.setBirthday(rs.getDate("Birthday"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        logger.debug(pi);
        return pi;
    }

    @Override
    public PatientInfoEntity getByPK(int key) {
        statement = null;
        connection = ConnectionPool.getConnection();
        try {
            statement = connection.prepareStatement(Queries.GET_PATIENT_BY_ID);
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            PatientInfoEntity pi = new PatientInfoEntity();
            if (rs.next()) {
                pi = create(rs);
            }
            logger.debug(pi);
            return pi;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public boolean checkEmail(String email) {
        PreparedStatement patient;
        ResultSet rsPatient = null;
        connection = ConnectionPool.getConnection();
        try {
            patient = connection.prepareStatement(Queries.GET_PATIENT_BY_EMAIL);
            patient.setString(1, email);
            rsPatient = patient.executeQuery();
            logger.debug(rsPatient);
            return rsPatient.next();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
            closeResultSet(rsPatient);
        }
    }

    @Override
    public PatientInfoEntity login(String email, String password) {
        PreparedStatement patient;
        ResultSet rsPatient = null;
        try {
            connection = ConnectionPool.getConnection();
            patient = connection.prepareStatement(Queries.GET_PATIENT_BY_EMAIL);
            patient.setString(1, email);

            rsPatient = patient.executeQuery();
            if ((rsPatient.next()) && rsPatient.getString("e_mail").equals(email)
                    && rsPatient.getString("Password").equals(password)) {
                return create(rsPatient);
            }
            return null;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
            closeResultSet(rsPatient);
        }
    }

    @Override
    public boolean update(PatientInfoEntity object) {
        return false;
    }

    @Override
    public void delete(PatientInfoEntity object) {

    }

    @Override
    public List<PatientInfoEntity> getAll() {
        List<PatientInfoEntity> list = new ArrayList<PatientInfoEntity>();
        String sql = Queries.GET_ALL_PATIENT;
        PreparedStatement stm;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(create(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
            closeResultSet(rs);
        }
        logger.debug(list);
        return list;
    }


    /**
     * Close connection
     *
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
