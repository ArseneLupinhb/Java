package com.adou.syds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.adou.syds.dao.ImageDao;
import com.adou.syds.domain.Image;
import com.adou.syds.utils.DeleteFile;
import com.adou.syds.utils.JdbcUtil;

public class ImageDaoImpl implements ImageDao {

	JdbcUtil db = new JdbcUtil();
	int size = 10;// 每页图片数

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	/**
	 * 添加图片
	 */
	public boolean addImage(Image image) throws SQLException {
		String sql = "INSERT INTO syds_image ( user_id, image_url, TYPE, album_id)VALUES(?, ?, ?,?)";
		Object[] params = { image.getUser_id(),
			    image.getImage_url(),			
			    image.getType(), 
			    image.getAlbum_id() };

		System.err.println(sql);
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除图片，根据图片id
	 */
	public boolean deleteImage(Image image) throws SQLException {
		
		String sql = "DELETE  FROM syds_image WHERE id = ?";
		Object[] params = { image.getId() };
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回一页数据
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Image> list(int currentPage, int size, String ip,
			String condition) {
		ArrayList imageList = new ArrayList();
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = this.db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		String sql = "";
		int nowSet = 1 + (currentPage - 1) * size;
		int nowSetCount = 1;
		if (condition != "")
			sql = "select * from syds_image  " + condition + " limit ?,?";
		else
			sql = "select * from syds_image limit ?,?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, (currentPage - 1) * size);
			pre.setInt(2, size);
			rs = pre.executeQuery();
			ResultSet rs1 = null;
			while (rs.next()) {
				Image im = new Image();
				im.setId(rs.getInt("id"));
				im.setTitle(rs.getString("title"));
				im.setUser_id(rs.getInt("user_id"));
				im.setPlace(rs.getString("place"));
				im.setImage_url(rs.getString("image_url"));
				im.setImage_backName(rs.getString("image_backName"));
				im.setIntroduction(rs.getString("introduction"));

				im.setPublish_time(rs.getDate("publish_time"));
				im.setType(rs.getInt("type"));
				im.setRank(rs.getInt("rank"));
				im.setIsRecommend(rs.getInt("isRecommend"));
				im.setIsPass(rs.getInt("isPass"));
				im.setNote(rs.getString("note"));
				int id = rs.getInt("id");
				im.setNowSet(nowSet);

				imageList.add(im);
				nowSetCount++;
				nowSet = (currentPage - 1) * size + nowSetCount;
				String sql1 = "select id from syds_praise where image_id=" + id
						+ " and ipAddress =" + "'" + ip + "'";
				try {
					pre = conn.prepareStatement(sql1);
					rs1 = pre.executeQuery();
					if (rs1.next())
						im.setZanFlag(rs.getInt("id"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageList;
	}

	/**
	 * 修改图片，以图片id来修改
	 */
	@Override
	public boolean alertImage(Image image) throws SQLException {
		String sql = "UPDATE syds_image SET title = ?, introduction = ? WHERE id = ?";
		Object[] params = { image.getTitle(), image.getIntroduction(),
				image.getId() };
		qr.update(sql, params);
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过用户id统计用户总的图片数
	 */
	@Override
	public int countImageByUser(int id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM syds_image WHERE user_id = ?";
		Object[] params = { id };
		Number countImage = (Number) qr.query(sql, new ScalarHandler(), params);
		return countImage.intValue();
	}

	/**
	 * 通过相册id统计用户总的图片数
	 */
	@Override
	public int countImageByAlbum(int album_id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM syds_image WHERE album_id = ?";
		Object[] params = { album_id };
		Number countImage = (Number) qr.query(sql, new ScalarHandler(), params);
		return countImage.intValue();
	}

	@Override
	public int count() {
		int total = 0;
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		String sql = "select count(*) total from syds_image";
		try {
			conn = this.db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			} else {
				total = 0;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 通过相册id查询图片
	 */
	@Override
	public List<Image> queryImagesByAlbum_id(int album_id)
			throws SQLException {
		
		String sql = "SELECT * FROM syds_image WHERE album_id = ?";
		Object[] params = {album_id};
		return (ArrayList<Image>) qr.query(sql, new BeanListHandler<Image>(Image.class),params);
	}
	public Image queryImageByImageId(int imageId) {
		System.out.println("通过图片id获取图片！");
		String sql = "SELECT * FROM syds_image WHERE id = ?";
		Object[] params = { imageId };
		try {
			return (Image) qr.query(sql,
					new BeanListHandler<Image>(Image.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean deleteImageById(int imageId) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("图片删除！！");
		String sql = "update syds_image set isDelete=1 where id = " + imageId;
		Connection conn = null;
		conn = JdbcUtil.getConnection();
//		conn.prepareStatement(sql).execute();
		return conn.prepareStatement(sql).execute();
	}
	
	public int Pass(int id) {

		int rs = 0;
		Connection conn = null;
		PreparedStatement pre = null;
		String sql = "update syds_image set isPass = 1 where id = " + id;
		try {
			conn = JdbcUtil.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}


}
