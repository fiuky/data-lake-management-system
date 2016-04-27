package project.webComponents;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import project.database.User;
import project.database.UserJdbcTemplate;

public class SessionHelperFunctions {
	
	@Autowired
	@Qualifier("userJdbcBean")
	static UserJdbcTemplate userJdbc;
	
	static boolean isLoggedIn(HttpSession session) {
		Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
		if (isAuthenticated == null) {
			session.setAttribute("isAuthenticated", false);
			return false;
		}
		else {
			if (isAuthenticated)
				return true;
			else 
				return false;
		}
	}
	
	static boolean login(HttpSession session, String username, String password) {
		User user = userJdbc.getUser(username);
		if (user.getPassword().equals(password)) {
			session.setAttribute("isAuthenticated", true);
			return true;
		}
		session.setAttribute("isAuthenticated", false);
		return false;
	}
	
}
