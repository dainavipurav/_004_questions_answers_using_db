package com.abc.learning.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QAServlet")
public class QAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PrintWriter out = response.getWriter();

			Statement statement = con.createStatement();
			ResultSet set = statement.executeQuery("select * from questions");

			out.print("<!DOCTYPE html>");
			out.print("<html>");

			out.println("<style>");
			out.println("input[type=submit], a {");
			out.println("width: 100%;");
			out.println("background-color: #4CAF50;");
			out.println("font-size: 16px;");
			out.println("color: white;");
			out.println("padding: 14px 20px;");
			out.println("margin: 8px 0;");
			out.println("border: none;");
			out.println("align-content:center;");
			out.println("border-radius: 4px;");
			out.println("cursor: pointer;");
			out.println("}");
			out.println("input[type=submit]:hover {");
			out.println("background-color: #45a049;");
			out.println("}");
			out.println("div {");
			out.println("border-radius: 5px;");
			out.println("background-color: #f2f2f2;");
			out.println("padding: 20px;");
			out.println("align-items: center;");
			out.println("align-content: center;");
			out.println("}");
			out.println("</style>");
			
			out.print("<body>");
			out.print("<h3>Give answers of the following questions</h3	>");
			out.print("<form method = \"post\" action=\"ResultServlet\">");

			int i=1;
			
			while (set.next()) {
				String qId = set.getString("id");
				String op1 = set.getString("op1");
				String op1Id = qId + op1;

				String op2 = set.getString("op2");
				String op2Id = qId + op2;

				String op3 = set.getString("op3");
				String op3Id = qId + op3;

				String op4 = set.getString("op4");
				String op4Id = qId + op4;

				System.out.println("qId : "+qId);

				out.print(i + ".");
				out.print(set.getString("ques"));
				out.print("<br>");

				out.print("<input type=\"radio\" id=\"" + op1Id + "\"name=\"" + qId + "\" value=\"" + op1 + "\">\r\n");
				out.print("<label for=\"" + op1Id + "\">" + op1 + "</label>");
				out.print("<br>");

				out.print("<input type=\"radio\" id=\"" + op2Id + "\"name=\"" + qId + "\" value=\"" + op2 + "\">\r\n");
				out.print("<label for=\"" + op2Id + "\">" + op2 + "</label>");
				out.print("<br>");

				out.print("<input type=\"radio\" id=\"" + op3Id + "\"name=\"" + qId + "\" value=\"" + op3 + "\">\r\n");
				out.print("<label for=\"" + op3Id + "\">" + op3 + "</label>");
				out.print("<br>");

				out.print("<input type=\"radio\" id=\"" + op4Id + "\"name=\"" + qId + "\" value=\"" + op4 + "\">\r\n");
				out.print("<label for=\"" + op4Id + "\">" + op4 + "</label>");
				out.print("<br>");
				out.print("<br>");

				i++;
			}
			out.print("<input type=\"submit\" value=\"Submit and check your result\" />");
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
			out.print("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
