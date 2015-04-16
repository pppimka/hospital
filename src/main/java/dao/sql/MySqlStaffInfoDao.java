package dao.sql;

import dao.StaffDao;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ann_ on 02.02.15.
 */
public class MySqlStaffInfoDao implements StaffDao {
    public static final Logger logger = Logger.getLogger(MySqlStaffInfoDao.class);

    private PreparedStatement statement;
    private Connection connection;

    public MySqlStaffInfoDao() {
    }

    @Override
    public StaffInfoEntity create(ResultSet rs) {
        StaffInfoEntity staffInfo = new StaffInfoEntity();
        try {
            staffInfo.setIdStaff(rs.getInt("ID_Staff"));
            staffInfo.setName(rs.getString("Name"));
            staffInfo.setSurname(rs.getString("Surname"));
            staffInfo.setTypeStaff(rs.getString("TypeStaff"));
            staffInfo.setPhoneNumber(rs.getString("PhoneNumber"));
            staffInfo.setBirthday(rs.getDate("Birthday"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.debug(staffInfo);
        return staffInfo;
    }

    @Override
    public StaffInfoEntity getByPK(int key) {
        ResultSet rs = null;
        statement = null;
        connection = ConnectionPool.getConnection();
        StaffInfoEntity pi = null;
        try {
            statement = connection.prepareStatement(Queries.GET_STAFF_INFO_BY_ID);
            statement.setInt(1, key);
            rs = statement.executeQuery();

            if (rs.next()) {
                pi = create(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
            closeResultSet(rs);
        }
        logger.debug(pi);
        return pi;
    }

    @Override
    public StaffInfoEntity login(String email, String password) {
        PreparedStatement staff;
        ResultSet rsStaff = null;
        try {
            connection = ConnectionPool.getConnection();
            staff = connection.prepareStatement(Queries.GET_STAFF_BY_EMAIL);
            staff.setString(1, email);

            rsStaff = staff.executeQuery();
            if ((rsStaff.next()) && rsStaff.getString("e_mail").equals(email)
                    && rsStaff.getString("Password").equals(password)) {
                return create(rsStaff);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
            closeResultSet(rsStaff);
        }
        return null;
    }

    @Override
    public boolean update(StaffInfoEntity object) {
        return false;
    }

    @Override
    public void delete(StaffInfoEntity object) {

    }

    @Override
    public List<StaffInfoEntity> getAll() {
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
