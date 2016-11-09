package trainning.broad.servlet.controller.user.post;

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

@WebServlet(urlPatterns = { "/post/details" })
public class Details extends HttpServlet {

	PostBusiness postBusiness;

	public Details() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int postId = Integer.parseInt(req.getParameter(Constants.ATTR_POST_ID));

		try {
			PostUserTag postUserTagComment = postBusiness.getPostDetails(postId);

			if (Helpers.isEmpty(postUserTagComment)) {
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			} else {
				req.setAttribute(Constants.POST_USER_TAG_COMMENT, postUserTagComment);
				Links.fowardTo(req, resp, Constants.POST_DETAIL_JSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}