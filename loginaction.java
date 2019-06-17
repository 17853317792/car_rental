package cn.wzf.login;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import cn.wzf.bean.User;
import cn.wzf.dao.user_dao;
/**
 * Servlet implementation class loginaction
 */
public class loginaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		user_dao userdao = new user_dao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User usertest = new User();
		usertest.setUsername(username);
		usertest.setPassword(password);
		try {
			User select_user = userdao.select_user(usertest);
			if(select_user == null)
			{
				JOptionPane option = new JOptionPane();
				option.setFont(new Font("”◊‘≤", Font.ITALIC, 40));
				option.setForeground(Color.BLUE);
				option.showMessageDialog(null, "’À∫≈√‹¬Î¥ÌŒÛ", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE); 
				
				response.sendRedirect("/Car_Rental/login.jsp");
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("user", select_user);
				
				response.sendRedirect("/Car_Rental/index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
