package dao.sql;

import dao.DiagnosisInfoDao;;
import model.DiagnosisInfoEntity;
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
public class MySqlDiagnosisInfoDao implements DiagnosisInfoDao {
    public static final Logger logger = Logger.getLogger(MySqlDiagnosisInfoDao.class);
    private PreparedStatement statement;
    private Connection connection;

    public MySqlDiagnosisInfoDao() {
    }

    @Override
    public DiagnosisInfoEntity create(ResultSet rs) {
        DiagnosisInfoEntity pi = new DiagnosisInfoEntity();
        try {
            pi.setIdDiagnosis(rs.getInt("ID_Diagnosis"));
            pi.setName(rs.getString("Name"));
            pi.setDescription(rs.getString("Description"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        logger.debug(pi);
        return pi;
    }

    @Override
    public DiagnosisInfoEntity getByPK(int key) {
        ResultSet rs = null;
        statement = null;
        connection = ConnectionPool.getConnection();
        try {
            statement = connection.prepareStatement(Queries.GET_DIAGNOSIS_INFO_BY_ID);
            statement.setInt(1, key);
            rs = statement.executeQuery();
            DiagnosisInfoEntity pi = null;
            if (rs.next()) {
                pi = create(rs);
            }
            logger.debug(pi);
            return pi;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
            closeResultSet(rs);
        }
        return null;
    }

    @Override
    public boolean update(DiagnosisInfoEntity object) {
        return false;
    }

    @Override
    public void delete(DiagnosisInfoEntity object) {

    }

    @Override
    public List<DiagnosisInfoEntity> getAll() {
        ResultSet rs = null;
        statement = null;
        connection = ConnectionPool.getConnection();
        try {
            statement = connection.prepareStatement(Queries.GET_ALL_DIAGNOSIS);
            rs = statement.executeQuery();
            List<DiagnosisInfoEntity> pi = new ArrayList<DiagnosisInfoEntity>();
            while (rs.next()) {
                pi.add(create(rs));
            }
            logger.debug(pi);
            return pi;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
            closeResultSet(rs);
        }
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
