package command;

import command.logic.NeedlessTreatmentException;
import command.logic.SetDiagnosisLogic;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ann_ on 25.02.15.
 */
public class GoToSetDiagnosisCommand extends Command {

    public static final Logger logger = Logger.getLogger(GoToSetDiagnosisCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws NeedlessTreatmentException {
        SetDiagnosisLogic diagnosisLogic = new SetDiagnosisLogic(daoFactory, (StaffInfoEntity) req.getAttribute("staff_info"));
        req.setAttribute("patient_list", diagnosisLogic.getAllPatientInfo());
        req.setAttribute("diagnosis_list", diagnosisLogic.getAllDiagnosis());
        req.setAttribute("prescription_list", diagnosisLogic.getAllPrescriptions());
        logger.debug("set diagnosis page");
        return "/jsp/setDiagnosis.jsp";
    }
}
