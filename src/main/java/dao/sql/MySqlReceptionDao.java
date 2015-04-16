package dao.sql;

import dao.ReceptionDao;
import model.ReceptionEntity;
import org.apache.log4j.Logger;
import utils.ConnectionPool;

import java.sql.*;
import java.util.List;

/**
 * Created by ann_ on 02.02.15.
 */
public class MySqlReceptionDao implements ReceptionDao {
    
    public static final Logger logger = Logger.getLogger(MySqlDiagnosisInfoDao.class);

    Connection connection;

    @Override
    public ReceptionEntity create(ResultSet rs) {
        return null;
    }

    @Override
    public ReceptionEntity getByPK(int key) {
        return null;
    }

    @Override
    public boolean update(ReceptionEntity object, Connection connection) {
        this.connection = connection;
        String sql = Queries.UPDATE_RECEPTION_RECORD;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, object.getDateOfDischarge());
            statement.setInt(2, object.getIdFinalDiagnosis());
            statement.setInt(3, object.getIdRegistration());
            if (statement.executeUpdate() == 1){
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
    public void delete(ReceptionEntity object) {

    }

    @Override
    public Integer add(ReceptionEntity object, Connection connection) {
        connection = ConnectionPool.getConnection();
        String sql = Queries.ADD_NEW_RECEPTION_RECORD;
        PreparedStatement statement;
        ResultSet res = null;
        Integer i = 0;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(2, object.getIdPatient());
            statement.setInt(3, object.getIdDoctor());
            statement.setDate(1, object.getDateOfAdmission());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            res.next();
            i = res.getInt(1);
            logger.debug(i);
            return i;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            closeResultSet(res);
            closeConnection();
        }
        return i;
    }

    @Override
    public List<ReceptionEntity> getAll() {
        return null;
    }

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
