package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;
import entity.UserInfo;
import impl.UserInfoServiceImpl;
import json.JackJsonUtils;
import json.ListObject;
import json.ResponseUtils;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserInfoDao userInfoDao = new UserInfoDao();
       
    public UserInfoServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request. setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		String path ="userinfo.jsp";
		if(method.equals("list")){
			
			List<UserInfo> list = userInfoDao.findAll();
			request.setAttribute("list", list);
		}else if(method.equals("add")){
			String id_phone_number = request.getParameter("phone");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			UserInfo userInfo = new UserInfo();
			userInfo.setId_phone_number(Long.parseLong(id_phone_number));
			userInfo.setName(name);
			userInfo.setPhone(phone);
			userInfo.setEmail(email);
			userInfo.setNickname(nickname);
			userInfo.setPassword(password);
			userInfoDao.add(userInfo);
			path ="UserInfoServlet?method=list";
		}else if(method.equals("delete")){
			String  phone = request.getParameter("phone");
			Long id_phone_number = Long.parseLong(phone);
			userInfoDao.delete(id_phone_number);
			path ="UserInfoServlet?method=list";
		}else if(method.equals("toedit")){
			   String id_phone_number = request.getParameter("phone");
			   Long id =  Long.parseLong(id_phone_number);
	    	   UserInfo userInfo = userInfoDao.findById(id);
	    	   path="edit.jsp";
	    	   request.setAttribute("userInfo", userInfo);
	    }else if(method.equals("edit")){
			String id_phone_number = request.getParameter("id_phone_number");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			UserInfo userInfo = new UserInfo();
			userInfo.setId_phone_number(Long.parseLong(id_phone_number));
			userInfo.setName(name);
			userInfo.setPhone(phone);
			userInfo.setEmail(email);
			userInfo.setNickname(nickname);
			userInfo.setPassword(password);
			userInfoDao.update(userInfo);
	    	path ="UserInfoServlet?method=list";
	       }else if(method.equals("getAllUserInfo")){
	    	List<UserInfo> list = new UserInfoServiceImpl().getAllUserInfo();  
	   	    ListObject listObject=new ListObject();  
	   	    listObject.setItems(list);  
	   	    String responseText = JackJsonUtils.toJson(listObject);  
	   	    ResponseUtils.renderJson(response, responseText); 
	   	    return;
	       }
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
