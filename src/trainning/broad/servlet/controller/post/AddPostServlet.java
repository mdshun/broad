package trainning.broad.servlet.controller.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.GoTo;

@WebServlet(urlPatterns = { "/post/add" })
public class AddPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public AddPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GoTo.fowardTo(req, resp, Constants.POST_ADD_JSP);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postName = req.getParameter(Constants.POST_NAME);
		String content = req.getParameter(Constants.ATTR_CONNTENT);
		String[] tags = req.getParameterValues(Constants.TAG);
		try {
			int userId = Helpers.getUserFromSession(req).getUserId();
			postBusiness.addPost(postName, content, tags, userId);
			req.setAttribute(Constants.MESSAGE, Constants.ADD_SUCCESS);
			GoTo.fowardTo(req, resp, Constants.POST_ADD_JSP);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute(Constants.ERROR, Constants.ADD_ERROR);
			GoTo.fowardTo(req, resp, Constants.POST_ADD_JSP);
		}
	}
}
