package command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ann_ on 24.02.15.
 */
public class ExitCommand extends Command {
    public static final Logger logger = Logger.getLogger(ExitCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        logger.debug(session);
        session.invalidate();
        return "/authorization.jsp";
    }
}
