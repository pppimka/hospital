package command;

import command.logic.PatientInfoLogic;
import command.logic.StaffLogic;
import dao.PatientDao;
import dao.StaffDao;
import model.PatientInfoEntity;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ann_ on 03.02.15.
 */
public class AuthorizationCommand extends Command {
    public static final Logger logger = Logger.getLogger(AuthorizationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getParameter("eMail");
        String password = req.getParameter("password");
        PatientDao patientDao = daoFactory.getPatientDao();
        StaffDao staffDao = daoFactory.getStaffDao();

        PatientInfoEntity patientInfo = patientDao.login(email, password);
        StaffInfoEntity staffInfo = staffDao.login(email, password);
        logger.debug(staffInfo);
        logger.debug(patientInfo);
        if (patientInfo != null) {
            PatientInfoLogic infoLogic = new PatientInfoLogic(daoFactory, patientInfo);
            req.getSession().setAttribute("patient_info", infoLogic.getPatientInfo());
            logger.debug("patient page");
            return "/jsp/patientMainPage.jsp";
        } else if (staffInfo != null) {
            if (staffInfo.getTypeStaff().equals("Doctor")) {
                StaffLogic staffLogic = new StaffLogic(daoFactory, staffInfo);
                req.getSession().setAttribute("staff_info", staffInfo);
                req.getSession().setAttribute("info_about_patient", staffLogic.getAllPatientsInfoForDoctor());
                logger.debug("doctor page");
                return "/jsp/doctorMainPage.jsp";
            } else {
                StaffLogic staffLogic = new StaffLogic(daoFactory, staffInfo);
                req.getSession().setAttribute("staff_info", staffInfo);
                req.getSession().setAttribute("info_about_patient", staffLogic.getPatientsInfoForNurse());
                logger.debug("nurse page");
                return "/jsp/nurseMainPage.jsp";
            }
        }
        logger.debug("registration page");
        return "/jsp/registration.jsp";
    }
}
