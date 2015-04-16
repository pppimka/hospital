package utils;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ann_ on 24.02.15.
 */
public class CommandButton extends BodyTagSupport implements DynamicAttributes {

    public static final Logger logger = Logger.getLogger(CommandButton.class);

    private Map<String, Object> tagAttributes = new HashMap<String, Object>();

    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doAfterBody() throws JspException {
        StringBuilder builder;

        try {
            builder = new StringBuilder();
            builder.append("<form method='post' action=''>");
            builder.append("<input type='hidden' name='command' value='").append(command).append("'/>");
            for (String s : tagAttributes.keySet()) {
                builder.append("<input type='hidden' name='").append(s).append("' value='").append(tagAttributes.get(s)).append("'/>");
            }
            builder.append("<input type='submit' value='").append(this.getBodyContent().getString()).append("'>");
            builder.append("</form>");
            logger.debug(builder.toString());
            getBodyContent().getEnclosingWriter().print(builder.toString());

        } catch (IOException e) {
                logger.error(e.getMessage());
            e.printStackTrace();
        }
        return SKIP_BODY;

    }

    @Override
    public void setDynamicAttribute(String uti, String name, Object value) throws JspException {
        logger.debug(value);
        tagAttributes.put(name, value);
    }
}
