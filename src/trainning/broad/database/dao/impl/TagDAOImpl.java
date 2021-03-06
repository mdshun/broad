package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import trainning.broad.bean.Tag;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.DAOHelpers;

public class TagDAOImpl extends GenericDAOImpl<Tag> implements TagDAO {

	public TagDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_TAG;
		this.con = con;
		this.id = Constants.ATTR_TAG_ID;
	}

	@Override
	public int save(String tagName) throws SQLException {

		PreparedStatement statement = null;
		ResultSet resultKey;
		String query = "INSERT INTO " + tableName + "(" + Constants.ATTR_TAG_NAME + ")" + " VALUES(?)";
		statement = con.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
		statement.setString(1, tagName);
		statement.executeUpdate();
		resultKey = statement.getGeneratedKeys();
		return DAOHelpers.getGenerateKey(resultKey);

	}

	@Override
	public List<Tag> search(String keyWord) throws SQLException {

		PreparedStatement statement = null;
		ResultSet result;
		String query = "SELECT * FROM " + tableName + " WHERE lower(" + Constants.ATTR_TAG_NAME + ") LIKE ? ";
		statement = con.prepareStatement(query);
		statement.setString(1, "%" + keyWord + "%");
		result = statement.executeQuery();
		return DAOHelpers.convertResultToTags(result);
	}
}
