package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CreateLogic;

public class CreateEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		/*
		 * int employeeCode=Integer.parseInt(req.getParameter("employeeCode"));
		 * System.out.println(employeeCode); String employeeName=
		 * req.getParameter("employeeName");
		 * 
		 * String Designation= req.getParameter("Designation"); double Salary=
		 * Double.parseDouble(req.getParameter("Salary"));
		 */
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			StringBuffer jb = new StringBuffer();
			String line = null;
			String str;

			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			str = jb.toString();
			JSONParser jp = new JSONParser();
			JSONObject jo = new JSONObject();
			jo = (JSONObject) jp.parse(str);
			String s1 = jo.get("employeeCode").toString();
			String s2 = jo.get("employeeName").toString();
			String s3 = jo.get("Designation").toString();
			String s4 = jo.get("Salary").toString();
			int employeeCode = Integer.parseInt(s1);
			String employeeName = s2;
			String Designation = s3;
			Double Salary = Double.parseDouble(s4);
			CreateLogic.insertData(employeeCode, employeeName, Designation, Salary);
			out.println("Record Inserted");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
