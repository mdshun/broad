package trainning.broad.servlet.controller.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/post/edit" })
public class EditPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public EditPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int postId = -1;

		if (!Helpers.isEmpty(req.getParameter(Constants.ATTR_POST_ID)))
			postId = Integer.parseInt(req.getParameter(Constants.ATTR_POST_ID));

		try {
			PostUserTag postUserTag = postBusiness.getPostDetails(postId);

			if (Helpers.isEmpty(postUserTag)) {
				req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
				Links.fowardTo(req, resp, Constants.HOME_PATH);
			} else {
				req.setAttribute(Constants.POST_USER_TAG, postUserTag);
				Links.fowardTo(req, resp, Constants.POST_EDIT_JSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postName = req.getParameter(Constants.POST_NAME);
		String content = req.getParameter(Constants.ATTR_CONNTENT);
		int postId = Integer.parseInt(req.getParameter(Constants.ATTR_POST_ID));
		// String[] tags = req.getParameterValues(Constants.TAG);

		try {
			postBusiness.editPost(postId, postName, content);
			req.setAttribute(Constants.MESSAGE, Constants.EDIT_SUCCESS);
			Links.fowardTo(req, resp, Constants.POST_EDIT_JSP);

		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute(Constants.ERROR, Constants.EDIT_ERROR);
			Links.fowardTo(req, resp, Constants.POST_EDIT_JSP);
		}
	}
}
