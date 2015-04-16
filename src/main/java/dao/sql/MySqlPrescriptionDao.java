package dao.sql;

import dao.PrescriptionDao;
import model.PrescriptionEntity;
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
public class MySqlPrescriptionDao implements PrescriptionDao {

    public static final Logger logger = Logger.getLogger(MySqlPrescriptionDao.class);

    private PreparedStatement statement;
    private Connection connection;

    @Override
    public PrescriptionEntity create(ResultSet rs) {
        PrescriptionEntity pi = new PrescriptionEntity();
        try {
            pi.setIdPrescription(rs.getInt("ID_Prescription"));
            pi.setDrugs(rs.getString("Drugs"));
            pi.setOperation(rs.getString("Operation"));
            pi.setProcedure(rs.getString("Procedure"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        logger.debug(pi);
        return pi;
    }

    @Override
    public PrescriptionEntity getByPK(int key) {
        ResultSet rs = null;
        statement = null;

        connection = ConnectionPool.getConnection();
        try {
            statement = connection.prepareStatement(Queries.GET_PRESCRIPTION_INFO_BY_ID);
            statement.setInt(1, key);
            rs = statement.executeQuery();
            PrescriptionEntity pi = null;
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
            closeResultSet(rs);
        }
    }

    @Override
    public boolean update(PrescriptionEntity object) {
        return false;
    }

    @Override
    public void delete(PrescriptionEntity object) {

    }

    @Override
    public List<PrescriptionEntity> getAll() {
        ResultSet rs = null;
        statement = null;
        connection = ConnectionPool.getConnection();
        try {
            statement = connection.prepareStatement(Queries.GET_ALL_PRESCRIPTIONS);
            rs = statement.executeQuery();
            List<PrescriptionEntity> pi = new ArrayList<PrescriptionEntity>();
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
