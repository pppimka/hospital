package servlet;

import command.Command;
import command.RequestHelper;
import command.logic.NeedlessTreatmentException;
import model.StaffInfoEntity;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ann_ on 02.02.15.
 */
public class Controller extends HttpServlet {

    public static final Logger logger = Logger.getLogger(Controller.class);

    RequestHelper requestHelper = RequestHelper.getInstance();
    HttpSession session;
    public Controller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        session = req.getSession();
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        session = req.getSession();

        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;

        Command command = requestHelper.getCommand(req);
        logger.debug(command);
        try {
            page = command.execute(req, resp);
        } catch (NeedlessTreatmentException e) {
            logger.error(e.getMessage());
            StaffInfoEntity staffInfo = (StaffInfoEntity) req.getSession().getAttribute("staff_info");
            if (staffInfo.getTypeStaff().equals("Doctor")){
                page =  "/jsp/doctorMainPage.jsp";
            } else {
                page =  "/jsp/nurseMainPage.jsp";
            }
        }

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
