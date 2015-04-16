package command;

import command.logic.SetDiagnosisLogic;
import command.logic.Wrapper;
import dao.PatientDiagnosisDao;
import dao.ReceptionDao;
import model.ReceptionEntity;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;
import utils.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by ann_ on 24.02.15.
 */
public class DischargePatientCommand extends Command {
    public static final Logger logger = Logger.getLogger(DischargePatientCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StaffInfoEntity staffInfo = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
        String key = req.getParameter("info");
        Map<String,Wrapper> map = (Map<String,Wrapper>) req.getSession().
                getAttribute("healthy_patient_list");
        Wrapper wrapper = map.get(key);
logger.debug(wrapper);
        PatientDiagnosisDao patientDiagnosisDao = daoFactory.getPatientDiagnosisDao();
        ReceptionDao receptionDao = daoFactory.getReceptionDao();
        ReceptionEntity receptionEntity = new ReceptionEntity();
        receptionEntity.setIdRegistration(wrapper.getPatientsDiagnosisEntity().getIdReception());
        receptionEntity.setIdDoctor(wrapper.getPatientsDiagnosisEntity().getIdPatient());
        receptionEntity.setIdPatient(wrapper.getPatientsDiagnosisEntity().getIdPatient());
        receptionEntity.setDateOfAdmission(wrapper.getPatientsDiagnosisEntity().getDate());
        receptionEntity.setDateOfDischarge(Date.valueOf(LocalDate.now()));
        receptionEntity.setIdFinalDiagnosis(1);
        wrapper.setReceptionEntity(receptionEntity);
        logger.debug(wrapper);
        Connection connection = ConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            receptionDao.update(wrapper.getReceptionEntity(), connection);
            patientDiagnosisDao.delete(wrapper.getPatientsDiagnosisEntity(), connection);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        SetDiagnosisLogic diagnosisLogic = new SetDiagnosisLogic(daoFactory, staffInfo);
        req.getSession().setAttribute("healthy_patient_list", diagnosisLogic.getAllHealthyPatientInfo());
        logger.debug("discharge patient page");
        return "/jsp/dischargePatient.jsp";
    }
}
