package command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by ann_ on 27.02.15.
 */
public class SetLanguageCommand extends Command {
    public static final Logger logger = Logger.getLogger(SetLanguageCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)   {
        String language = req.getParameter("lang");
        logger.debug(language);
        if (language.equals("Ukrainian") || language.equals("Українська")) {
            req.getSession().setAttribute("language", new Locale("uk", "UA"));
            logger.debug("ukr");
        }
        if (language.equals("English") || language.equals("Англійська")) {
            req.getSession().setAttribute("language", new Locale("en", "US"));
            logger.debug("eng");
        }
        logger.debug("authorization page");
        return "/authorization.jsp";
    }
}
