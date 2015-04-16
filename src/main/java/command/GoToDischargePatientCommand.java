package command;

import command.logic.NeedlessTreatmentException;
import command.logic.SetDiagnosisLogic;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ann_ on 25.02.15.
 */
public class GoToDischargePatientCommand extends Command{
    public static final Logger logger = Logger.getLogger(GoToDischargePatientCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws NeedlessTreatmentException {
        SetDiagnosisLogic diagnosisLogic = new SetDiagnosisLogic(daoFactory, (StaffInfoEntity) req.getSession().getAttribute("staff_info"));
        Map map = diagnosisLogic.getAllHealthyPatientInfo();
        logger.debug(map);
        if (map != null) {
            req.getSession().setAttribute("healthy_patient_list", diagnosisLogic.getAllHealthyPatientInfo());
        }
        logger.debug("discharge patient page");
        return "/jsp/dischargePatient.jsp";
    }
}
