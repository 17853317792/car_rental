package cn.wzf.login;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import cn.wzf.bean.Customer;
import cn.wzf.bean.User;
import cn.wzf.dao.Customer_dao;
import cn.wzf.dao.Customer_user_dao;
import cn.wzf.dao.user_dao;

/**
 * Servlet implementation class customer_loginaction
 */
public class customer_loginaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customer_loginaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String id = request.getParameter("customerid");
		String customerpid = request.getParameter("customerpid");
		long customerid = Long.parseLong(id);
		Customer_user_dao dao = new Customer_user_dao();
		User usertest = new User();
		usertest.setUsername(username);
		usertest.setPassword(password);
		Customer customertest = new Customer();
		Customer_dao customerdao = new Customer_dao();
		try {
			User select_user = dao.select_customeruser(usertest);
			Customer selectbyid_customer = customerdao.selectbyid_customer(customerid, customerpid);
			if(select_user == null)
			{
				JOptionPane option = new JOptionPane();
				option.setFont(new Font("Ó×Ô²", Font.ITALIC, 40));
				option.setForeground(Color.BLUE);
				option.showMessageDialog(null, "ÕËºÅÃÜÂë´íÎó", "´íÎó", JOptionPane.ERROR_MESSAGE); 
				response.sendRedirect("/Car_Rental/customer-login.jsp");
			}
			else if(selectbyid_customer == null)
			{
				JOptionPane option = new JOptionPane();
				option.setFont(new Font("Ó×Ô²", Font.ITALIC, 40));
				option.setForeground(Color.BLUE);
				option.showMessageDialog(null, "¹Ë¿ÍÐÅÏ¢´íÎó", "´íÎó", JOptionPane.ERROR_MESSAGE); 
				response.sendRedirect("/Car_Rental/customer-login.jsp");
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("customer_user", select_user);
				session.setAttribute("customer", selectbyid_customer);
				response.sendRedirect("/Car_Rental/customer-index.jsp");
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
