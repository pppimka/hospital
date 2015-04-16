package command;

import command.logic.NeedlessTreatmentException;
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
public class GiveDrugsCommand extends Command {

    public static final Logger logger = Logger.getLogger(GiveDrugsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws NeedlessTreatmentException {
        StaffInfoEntity staffInfo = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
        logger.debug(staffInfo);
        String key = req.getParameter("info");
        Map<String,Wrapper> map = (Map<String,Wrapper>) req.getSession().getAttribute("info_about_patient");
        Wrapper wrapper = map.get(key);
        int i = wrapper.getCurrentPrescription().getIdPrescription();
        switch (i){
            case 2: i = 1; break;
            case 5: i = 3; break;
            case 7: i = 4; break;
            case 8: i = 6; break;
            default: throw new NeedlessTreatmentException();
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
