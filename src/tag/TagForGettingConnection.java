package tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagForGettingConnection extends SimpleTagSupport {
	protected ResultSet rs;
	protected Statement st;
	protected Connection conn;

	@Override
	public void doTag() throws JspException, IOException {
		try {
			conn = (Connection) getJspContext().getAttribute("databaseConnection", PageContext.APPLICATION_SCOPE);
			st = conn.createStatement();
			rs = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
