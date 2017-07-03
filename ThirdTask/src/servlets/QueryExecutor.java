package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcConnections.OracleThinJDBC;

@WebServlet("/QueryExecutor")
public class QueryExecutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryExecutor() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OracleThinJDBC dbc = new OracleThinJDBC();
		dbc.setURL("Server.dpat.in", "ORCL", 1521);
		dbc.connect("practice", "practice123");
		Connection conn = dbc.getConnection();

		String query = (String) request.getParameter("query");

		PrintWriter out = response.getWriter();
		ResultSet rs;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);

			//table, formed by SQL query
			out.println("<table>");
			while (rs.next()) {
				out.println("<tr>");
				for (int i = 1; ; i++) {
					try {
						rs.getString(i);
						out.println("<td>");
						out.println(rs.getString(i));
						out.println("</td>");
					} catch (SQLException e) {
						break;
					}
				}
				out.println("</tr>");
			}
			out.println("</table>");

		} catch (SQLException e1) {
			out.println(e1);
		}
	}
}
