package com.abc.learning.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PrintWriter out = response.getWriter();

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<style>");
			out.print("table, th, td,tr {");
			out.print("border:1px solid black;");
			out.print("align-items: center;");
			out.print("align-content: center;");
			out.print("}");
			out.print("</style>");
			out.print("<body>");
			out.print("<h2>Question & Answer Table</h2>");
			out.print("<table style=\"width:100%\">");
			out.print("<tr>");
			out.print("<th>Question Id</th>");
			out.print("<th>Question</th>");
			out.print("<th>Expected Answer</th>");
			out.print("<th>Submitted Answer</th>");
			out.print("</tr>");

			Statement statement = con.createStatement();
			ResultSet questionResultSet = statement.executeQuery("select * from questions");
			Map<String, String> questionMap = new HashMap<>();
			
			while(questionResultSet.next()) {
				String qid = questionResultSet.getString("id");
				String qestion = questionResultSet.getString("ques");
				questionMap.putIfAbsent(qid, qestion);
			}
			
			ResultSet set = statement.executeQuery("select * from answers");

			int resultCount = 0;

			while (set.next()) {
				String qid = set.getString("qid");
				String expectedAns = set.getString("ans");
				String submittedAns = request.getParameter(qid);

				System.out.println("QID : " + qid);
				System.out.println("Question : " + questionMap.get(qid));
				System.out.println("expectedAns : " + expectedAns);
				System.out.println("submittedAns : " + submittedAns);

				out.print("<tr>");
				out.print("<td>" + qid + "</td>");
				out.print("<td>" + questionMap.get(qid) + "</td>");
				out.print("<td>" + expectedAns + "</td>");
				out.print("<td>" + submittedAns + "</td>");
				out.print("</tr>");

				if (submittedAns != null && submittedAns != "") {
					if (submittedAns.trim().toLowerCase().equals(expectedAns.trim().toLowerCase())) {
						resultCount = resultCount + 1;
						System.out.println("Ans matched : " + resultCount);
					} else {
						System.out.println("Ans not matched : " + resultCount);
					}
				}
			}
			out.print("</table>");
			out.print("<h3>Result</h3>");
			out.print("<h3>Your score for the quiz : " + resultCount + "</h3>");
			out.print("</body>");
			out.print("</html>");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
