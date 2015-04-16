package command;

import command.logic.NeedlessTreatmentException;
import dao.DaoFactory;
import dao.sql.MySqlDaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ann_ on 02.02.15.
 */
public abstract class Command {

    protected static DaoFactory daoFactory = new MySqlDaoFactory();

    public abstract String execute(HttpServletRequest req,
                                   HttpServletResponse res) throws NeedlessTreatmentException;
}
