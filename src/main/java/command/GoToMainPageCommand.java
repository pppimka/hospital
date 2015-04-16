package command;

import command.logic.NeedlessTreatmentException;
import command.logic.StaffLogic;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ann_ on 26.02.15.
 */
public class GoToMainPageCommand extends Command {
    public static final Logger logger = Logger.getLogger(GoToMainPageCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws NeedlessTreatmentException {
        StaffInfoEntity staffInfo = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
        StaffLogic staffLogic = new StaffLogic(daoFactory, staffInfo);
        logger.debug(staffInfo);
        req.getSession().setAttribute("info_about_patient", staffLogic.getAllPatientsInfoForDoctor());
        return "/jsp/doctorMainPage.jsp";
    }
}
