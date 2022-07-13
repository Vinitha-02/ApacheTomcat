package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CreateLogic;
import logic.DeleteLogic;

public class DeleteEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		 PrintWriter out= res.getWriter();
		//int employeeCode=Integer.parseInt(req.getParameter("employeeCode"));
			
		PreparedStatement ps=null;
	    ResultSet r=null;
	    StringBuffer jb = new StringBuffer();
		String line = null;
		String str;
		String s;
		int empId;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			str = jb.toString();
			JSONParser jp = new JSONParser();

			JSONObject jo = new JSONObject();
			jo = (JSONObject) jp.parse(str);
			s = jo.get("employeeCode").toString();
			 int employeeCode = Integer.parseInt(s);
	    	DeleteLogic.delete(employeeCode);
			out.println("deleted the recorded");

      	
	    }catch(Exception e) 
      {
	 e.printStackTrace();}

}}
