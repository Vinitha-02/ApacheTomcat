package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CreateLogic;
import logic.ListLogic;

public class ListEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		// int employeeCode=Integer.parseInt(req.getParameter("employeeCode"));
		Statement stat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		StringBuffer jb = new StringBuffer();
		String line = null;
		String str;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			str = jb.toString();

			JSONParser jp = new JSONParser();

			JSONObject jo = new JSONObject();
			jo = (JSONObject) jp.parse(str);

			String s = jo.get("employeeCode").toString();

			int employeeCode = Integer.parseInt(s);
			JSONObject record = new JSONObject();

			out.println(ListLogic.List(employeeCode));

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
