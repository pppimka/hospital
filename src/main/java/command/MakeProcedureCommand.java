package command;

import command.logic.StaffLogic;
import command.logic.Wrapper;
import dao.PatientDiagnosisDao;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ann_ on 24.02.15.
 */
public class MakeProcedureCommand extends Command {

    public static final Logger logger = Logger.getLogger(MakeProcedureCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StaffInfoEntity staffInfo = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
        String key = req.getParameter("info");
        Map<String,Wrapper> map = (Map<String,Wrapper>) req.getSession().getAttribute("info_about_patient");
        Wrapper wrapper = map.get(key);
        logger.debug(wrapper);
        int i = wrapper.getCurrentPrescription().getIdPrescription();
        switch (i){
            case 3: i = 1; break;
            case 5: i = 2; break;
            case 6: i = 4; break;
            case 8: i = 7; break;
        }
        wrapper.getPatientsDiagnosisEntity().setCurrentPrescription(i);
        PatientDiagnosisDao diagnosisDao = daoFactory.getPatientDiagnosisDao();
        diagnosisDao.update(wrapper.getPatientsDiagnosisEntity());
        StaffLogic logic = new StaffLogic(daoFactory, staffInfo);
        logger.debug(wrapper);
        if (staffInfo.getTypeStaff().equals("Doctor")){
            req.getSession().setAttribute("info_about_patient", logic.getAllPatientsInfoForDoctor());
            logger.debug("doctor main page");
            return "/jsp/doctorMainPage.jsp";
        }
        req.getSession().setAttribute("info_about_patient", logic.getPatientsInfoForNurse());
        logger.debug("nurse main page");
        return "/jsp/nurseMainPage.jsp";
    }
}
