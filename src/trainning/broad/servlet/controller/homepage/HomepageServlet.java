package trainning.broad.servlet.controller.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.business.HomepageBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

@WebServlet(urlPatterns = { "" })
public class HomepageServlet extends HttpServlet {

	HomepageBusiness homepageBusiness;

	public HomepageServlet() {

		try {
			this.homepageBusiness = new HomepageBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Helpers.isOnline(req)) {
			try {
				List<PostUserTag> postUserTags = homepageBusiness.getDataHomepage();
				req.setAttribute(Constants.POST_USER_TAGS, postUserTags);
			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
		GoTo.fowardTo(req, resp, Constants.HOMEPAGE_JSP);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
