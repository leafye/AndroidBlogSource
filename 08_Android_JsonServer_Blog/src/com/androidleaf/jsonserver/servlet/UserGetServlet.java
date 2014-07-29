package com.androidleaf.jsonserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.androidleaf.jsonserver.service.JsonService;
import com.androidleaf.jsonserver.tools.JsonTools;

/**
 * Servlet implementation class UserGetServlet
 */
public class UserGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JsonService mService;
    /**
     * Default constructor. 
     */
    public UserGetServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		mService = new JsonService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String jsonString = "";
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("person")) {
			jsonString = JsonTools.createJsonString("person", mService
					.getPerson());
		} else if (action_flag.equals("listperson")) {
			jsonString = JsonTools.createJsonString("listperson", mService
					.getlistPerson());
		} else if (action_flag.equals("liststring")) {
			jsonString = JsonTools.createJsonString("liststring", mService
					.getListString());
		}else if(action_flag.equals("listmapperson")){
			jsonString = JsonTools.createJsonString("listmapperson", mService.getListMap());
		}
		out.println(jsonString);
		out.flush();
		out.close();
	}
}
