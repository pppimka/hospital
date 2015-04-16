package command;

import command.logic.SetDiagnosisLogic;
import command.logic.StaffLogic;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ann_ on 24.02.15.
 */
public class SetDiagnosisCommand extends Command {
    public static final Logger logger = Logger.getLogger(SetDiagnosisCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StaffInfoEntity staffInfo  = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
        SetDiagnosisLogic diagnosisLogic = new SetDiagnosisLogic(daoFactory, staffInfo);
        logger.debug(staffInfo);
        Integer idPatient =  Integer.parseInt(req.getParameter("patient_id"));
        Integer idDiagnosis = Integer.parseInt(req.getParameter("diagnosis_id"));
        Integer idPrescription = Integer.parseInt(req.getParameter("prescription_id"));

        diagnosisLogic.setDiagnosis(idPatient, idPrescription, idDiagnosis);
        StaffLogic staffLogic = new StaffLogic(daoFactory, staffInfo);
        req.getSession().setAttribute("staff_info", staffInfo);
        req.getSession().setAttribute("info_about_patient", staffLogic.getAllPatientsInfoForDoctor());
        logger.debug("doctor main page");
        return "/jsp/doctorMainPage.jsp";
    }
}
