package command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by ann_ on 02.02.15.
 */
public class RequestHelper {
    public static final Logger logger = Logger.getLogger(RequestHelper.class);

    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        commands.put("newPatient", new AddNewPatientCommand());
        commands.put("authorization", new AuthorizationCommand());
        commands.put("dischargePatient", new DischargePatientCommand());
        commands.put("setDiagnosis", new SetDiagnosisCommand());
        commands.put("giveDrugs", new GiveDrugsCommand());
        commands.put("makeOperation", new MakeOperationCommand());
        commands.put("makeProcedure", new MakeProcedureCommand());
        commands.put("goToSetDiagnosis", new GoToSetDiagnosisCommand());
        commands.put("goToDischarge", new GoToDischargePatientCommand());
        commands.put("goToMainPage", new GoToMainPageCommand());
        commands.put("changeLanguage", new SetLanguageCommand());
        commands.put("exit", new ExitCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        logger.debug(command);
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
            logger.debug(instance);
        }
        return instance;
    }
}
