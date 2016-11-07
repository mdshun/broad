package trainning.broad.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.InfoPostHomepage;
import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.Tag;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;

public class HomepageBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private TagDAO tagDAO;
	private PostTagDAO postTagDAO;

	public HomepageBusiness() {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<InfoPostHomepage> getDataForHomepage() {

		List<InfoPostHomepage> infoPostHomepages = new ArrayList<InfoPostHomepage>();
		List<Tag> tags;
		List<Post> posts;
		List<PostTag> postTags;
		postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
		userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
		postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
		tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);

		try {
			posts = postDAO.findAll();

			for (Post post : posts) {
				tags = new ArrayList<Tag>();
				InfoPostHomepage infoPostHomepage = new InfoPostHomepage();
				post.setContent(Helpers.cutString(post.getContent()));
				infoPostHomepage.setPost(post);
				infoPostHomepage.setUser(userDAO.findById(post.getUserId()));

				postTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, post.getPostId());

				for (PostTag postTag : postTags) {
					tags.add(tagDAO.findById(postTag.getTagId()));
				}

				infoPostHomepage.setTags(tags);
				infoPostHomepages.add(infoPostHomepage);
			}

			return infoPostHomepages;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			daoManager.close();
		}

	}

}
