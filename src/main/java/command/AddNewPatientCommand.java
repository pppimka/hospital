package command;

import dao.PatientDao;
import model.PatientInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 * Created by ann_ on 02.02.15.
 */
public class AddNewPatientCommand extends Command {

    public static final Logger logger = Logger.getLogger(AddNewPatientCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("eMail");
        PatientDao patientInfoDao = daoFactory.getPatientDao();

        if (patientInfoDao.checkEmail(email)) {
            return "/jsp/authorization.jsp";
        }
        if (request.getParameter("password").equals(request.getParameter("checkPassword"))){
            PatientInfoEntity newPatient = new PatientInfoEntity();

            newPatient.setName(request.getParameter("firstName"));
            newPatient.setSurname(request.getParameter("lastName"));
            newPatient.seteMail(email);
            newPatient.setBirthday(Date.valueOf(request.getParameter("birthday")));
            newPatient.setPhoneNumber(request.getParameter("phoneNumber"));
            newPatient.setPassword(request.getParameter("password"));
            if (patientInfoDao.addNewPatient(newPatient)) {
                logger.debug(newPatient);
                request.getSession().setAttribute("patient_info",newPatient);
                return "/jsp/patientMainPage.jsp";
            }
        }
        return "/jsp/registration.jsp";
    }
}
