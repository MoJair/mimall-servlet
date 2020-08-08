package com.mi.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 改成JNDI: Java Naming and Directory Interface, Java命名和目录接口
 * @author MoJair
 * @date 2020年8月6日
 * @param <T>
 */
public class DBHelper<T> {
	/*static {
		// 加载驱动 - 只需要在类第一次加载的时候执行一次
		try {
			Class.forName(ReadConfig.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 获取连接的方法
	 * @return 获取到的连接
	 */
	private Connection getConnection() {
		Connection con = null;
		try {
			//con = DriverManager.getConnection(ReadConfig.getInstance().getProperty("url"), ReadConfig.getInstance());
			//从连接池中获取一个空闲的连接
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/mimall");
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 给预编译块语句中的占位符?赋值
	 * @param pstmt
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 */
	private void setParams(PreparedStatement pstmt, Object ... params) {
		if (params == null || params.length <= 0) { // 说明没有参数给我,也就意味着执行的SQL语句中没有占位符?
			return;
		}

		for (int i = 0, len = params.length; i < len; i ++) {
			try {
				pstmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("第 " + (i + 1) + " 个参数注值失败...");
			}
		}
	}
	
	/**
	 * 给预编译块语句中的占位符?赋值
	 * @param pstmt
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 */
	private void setParams(PreparedStatement pstmt, List<Object> params) {
		if (params == null || params.isEmpty()) { // 说明没有参数给我， 也就意味着执行的SQL语句中没有占位符?
			return;
		}
		
		for (int i = 0, len = params.size(); i < len; i ++) {
			try {
				pstmt.setObject(i + 1, params.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("第 " + (i + 1) + " 个参数注值失败...");
			}
		}
	}

	/**
	 * 关闭资源的方法
	 * @param rs 要关闭的结果集
	 * @param pstmt 要关闭的预编译对象
	 * @param con 要关闭的连接
	 */
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新操作
	 * @param sql 要执行的更新语句，可以是insert、delete或update
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return
	 */
	public int update(String sql, Object ... params) {  // 采用不定参数形式
		int result = -1;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = this.getConnection();

			pstmt= con.prepareStatement(sql); // 预编译执行语句
			this.setParams(pstmt, params); //  给预编译执行语句中的占位符赋值
			result = pstmt.executeUpdate(); // 执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, con);
		}
		return result;
	}
	
	
	public int updates(List<String> sqls, List<List<Object>> params) {  // 采用不定参数形式
		int result = -1;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = this.getConnection();
			
			con.setAutoCommit(false); // 关闭自动事务提交
			
			for (int i = 0, len = sqls.size(); i < len; i ++) {
				pstmt= con.prepareStatement(sqls.get(i)); // 预编译执行语句
				this.setParams(pstmt, params.get(i)); //  给预编译执行语句中的占位符赋值
				result = pstmt.executeUpdate(); // 执行更新
			}
			
			con.commit(); // 提交事务
		} catch (SQLException e) {
			try {
				con.rollback();  // 回滚事务
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true); // 最终还是要开启自动事务提交
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.close(null, pstmt, con);
		}
		return result;
	}
	
	/**
	 * 更新操作
	 * @param sql 要执行的更新语句，可以是insert、delete或update
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return
	 */
	public int update(String sql, List<Object> params) {  // 采用不定参数形式
		int result = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = this.getConnection();
			
			pstmt= con.prepareStatement(sql); // 预编译执行语句
			this.setParams(pstmt, params); //  给预编译执行语句中的占位符赋值
			result = pstmt.executeUpdate(); // 执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, con);
		}
		return result;
	}

	/**
	 * 获取结果集中所有列的类名
	 * @param rs 结果集对象
	 * @return 
	 * @throws SQLException 
	 */
	private String[] getColumnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData(); // 获取结果集中的元数据
		int colCount = rsmd.getColumnCount(); // 获取结果集中列的数量
		String[] colNames = new String[colCount];
		for (int i = 1; i <= colCount; i ++) { // 循环获取结果集中列的名字
			colNames[i - 1] = rsmd.getColumnName(i).toLowerCase(); // 将列名改成小写后存到数组中
		}
		return colNames;
	}

	/**
	 * 查询
	 * @param sql 要执行的查询语句
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return 满足条件的数据 每一条数据存到一个map中以列名为键，以对应列的值位置，然后将每一条数据即map对象存到list中
	 */
	public List<Map<String, Object>> finds(String sql, Object ... params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			Map<String, Object> map = null;

			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);

			Object obj = null; // 列的数据
			String colType = null; // 返回的这个列的数据的类型名称
			Blob blob = null;
			byte[] bt = null;
			while(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, Object>();

				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					// 首先我们不必获取返回的这个列的数据的类型是不是blob，若干是blob那么我们就转成字节数组将这个数据存到本地
					obj = rs.getObject(colName);

					if (obj == null) {
						map.put(colName, obj);
						continue;
					}

					// 获取这个列值对象的类型
					colType = obj.getClass().getSimpleName();
					if ("BLOB".equals(colType)) {
						// 用blob获取，然后转成字节数据
						blob = rs.getBlob(colName);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(colName,  bt);
					} else {
						map.put(colName, obj);
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}
	
	public List<Map<String, Object>> finds(String sql, List<Object> params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			Map<String, Object> map = null;
			
			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			
			Object obj = null; // 列的数据
			String colType = null; // 返回的这个列的数据的类型名称
			Blob blob = null;
			byte[] bt = null;
			while(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, Object>();
				
				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					// 首先我们不必获取返回的这个列的数据的类型是不是blob，若干是blob那么我们就转成字节数组将这个数据存到本地
					obj = rs.getObject(colName);
					
					if (obj == null) {
						map.put(colName, obj);
						continue;
					}
					
					// 获取这个列值对象的类型
					colType = obj.getClass().getSimpleName();
					if ("BLOB".equals(colType)) {
						// 用blob获取，然后转成字节数据
						blob = rs.getBlob(colName);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(colName,  bt);
					} else {
						map.put(colName, obj);
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 查询
	 * @param sql 要执行的查询语句
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return 满足条件的数据 每一条数据存到一个map中以列名为键，以对应列的值位置，然后将每一条数据即map对象存到list中
	 */
	public List<Map<String, String>> gets(String sql, Object ... params) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			Map<String, String> map = null;

			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, String>();

				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					map.put(colName, rs.getString(colName));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}
	
	public List<Map<String, String>> gets(String sql, List<Object> params) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			Map<String, String> map = null;
			
			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, String>();
				
				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					map.put(colName, rs.getString(colName));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 查询单行
	 * @param sql 要执行的查询语句
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return 满足条件的数据 每一条数据存到一个map中以列名为键，以对应列的值位置，然后将每一条数据即map对象存到list中
	 */
	public Map<String, Object> find(String sql, Object ... params) {
		Map<String, Object> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询

			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);

			Object obj = null; // 列的数据
			String colType = null; // 返回的这个列的数据的类型名称
			Blob blob = null;
			byte[] bt = null;

			if(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, Object>();

				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					// 首先我们不必获取返回的这个列的数据的类型是不是blob，若干是blob那么我们就转成字节数组将这个数据存到本地
					obj = rs.getObject(colName);

					if (obj == null) {
						map.put(colName, obj);
						continue;
					}

					// 获取这个列值对象的类型
					colType = obj.getClass().getSimpleName();
					if ("BLOB".equals(colType)) {
						// 用blob获取，然后转成字节数据
						blob = rs.getBlob(colName);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(colName,  bt);
					} else {
						map.put(colName, obj);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return map;
	}
	
	public Map<String, Object> find(String sql, List<Object> params) {
		Map<String, Object> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			
			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			
			Object obj = null; // 列的数据
			String colType = null; // 返回的这个列的数据的类型名称
			Blob blob = null;
			byte[] bt = null;
			
			if(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, Object>();
				
				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					// 首先我们不必获取返回的这个列的数据的类型是不是blob，若干是blob那么我们就转成字节数组将这个数据存到本地
					obj = rs.getObject(colName);
					
					if (obj == null) {
						map.put(colName, obj);
						continue;
					}
					
					// 获取这个列值对象的类型
					colType = obj.getClass().getSimpleName();
					if ("BLOB".equals(colType)) {
						// 用blob获取，然后转成字节数据
						blob = rs.getBlob(colName);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(colName,  bt);
					} else {
						map.put(colName, obj);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return map;
	}

	/**
	 * 查询单行
	 * @param sql 要执行的查询语句
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return 满足条件的数据 每一条数据存到一个map中以列名为键，以对应列的值位置，然后将每一条数据即map对象存到list中
	 */
	public Map<String, String> get(String sql, Object ... params) {
		Map<String, String> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询


			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			if(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, String>();

				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					map.put(colName, rs.getString(colName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return map;
	}
	
	public Map<String, String> get(String sql, List<Object> params) {
		Map<String, String> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			
			
			// 如果获取结果集中列的类名 -> 取到列名后我们存到一个数组中，便于后面的循环取值 -> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			if(rs.next()) { // 每次循环得到一行数据
				map = new HashMap<String, String>();
				
				// 循环获取每一列的值，循环所有的列名，根据列名获取当前这一行这一列的值
				for (String colName : colNames) {
					map.put(colName, rs.getString(colName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return map;
	}

	/**
	 * 获取总记录数的方法  一行一列
	 * @param sql 要执行的查询语句
	 * @param params 要执行的sql语句中对应占位符?的值，即按照?的顺序给定的值
	 * @return 总记录数
	 */
	public int total(String sql, Object ... params) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			if(rs.next()) { // 每次循环得到一行数据
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return result;
	}
	
	public int total(String sql, List<Object> params) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); // 获取连接
			pstmt = con.prepareStatement(sql); // 预编译语句
			this.setParams(pstmt, params); // 给预编译语句中的占位符赋值
			rs = pstmt.executeQuery(); // 执行查询
			if(rs.next()) { // 每次循环得到一行数据
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return result;
	}
	
	/**
	 * 获取指定类中setter的方法
	 * @param clazz
	 * @return
	 */
	private Map<String, Method> getSetters(Class<?> clazz) {
		//获取給定类中的setter方法
		Method[] methods = clazz.getMethods(); //得到给定类中的所有公共方法
		
		//从中提取出setter方法, 已方法名为键, 对应的方法为值,注意:我这里会将所有的方法名全部转成小写字母的方法
		Map<String, Method> setters = new HashMap<String, Method>();
		
		String methodName = null;
		for (Method method : methods) {
			methodName = method.getName().toLowerCase(); //获取当前方法的方法名
			if (methodName.startsWith("set")) { //说明是set方法
				setters.put(methodName, method);
			}
		}
		return setters;
	}
	
	
	/**
	 * 泛型:参数化类型: 将类型由原来的具体的类型参数化，类似于方法中的变里参数，此时类型也定义成参数形式，即类型形参
	 * T (Type Java类) E (Element,在集合中使用，因为集合中存放的都是元素) ?表示不确定的java类型K (Key) V (Value) N (Number 数值类型)
	 * 基于实体类对象的查询
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> finds(Class<T> clazz, String sql, Object ... params) {
		List<T> list = new ArrayList<T>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译语句
			this.setParams(pstmt, params); //给预编译语 句中的占位符赋值
			
			rs = pstmt.executeQuery(); //执行查询
			
			//如果获取结果集中列的类名 ->取到列名后我们存到一 个数组中，便于后面的循环取值一> 如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			
			// Map<String, Method> setters = getSetters (clazz) ; //获取指定对象的setter方法
			//获取给定类中的setter方法
			
			//从中提取出setter方法， 已方法名为键，对应的方法为值，注意:我这里会将所有的方法名全部转成小写字母的方法
			Map<String, Method> setters = getSetters(clazz);
			
			String methodName = null;
			T t;
			Method method = null;
			Class<?>[] types = null;
			Class<?> type = null;
			while(rs.next()) { //每次循环得到-行数据，-行数据对应-个实体类对象
				t = clazz.newInstance(); //注意:此时需要调用类的无参构成
				
				for (String colName : colNames) { // 将这个列的值注入到对应的对象的属性中，所有需要找到对应的setter方法
					methodName = "set" + colName; //setsid
					
					//从setter方法找到对应的方法
					method = setters.get(methodName);
					if (method == null) {
						continue; // 直接执行下一次循环
					}
					
					//获取这个方法的参数列表，考虑到正常的setter方法只会有一-个参数， 所以我们只取第- -个参数的类型
					types = method.getParameterTypes();
					if (types == null) {	//说明没有形参
						continue;
					}
					
					type = types[0]; // 获取第一个形参的类型
					
					if (Integer.TYPE == type || Integer.class == type) {
						method.invoke(t, rs.getInt(colName)); //反向激活这个方法， 相当于t. setSid (value)
					} else if (Double.TYPE == type || Double.class == type) {
						method.invoke(t, rs.getDouble(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else{
						method.invoke(t, String.valueOf(rs.getObject(colName)));
					}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace ();
		} catch (IllegalArgumentException e) {
			e.printStackTrace ();
		} catch (InvocationTargetException e) {
			e.printStackTrace ();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}
	
	
	/**
	 * 查询单个对象
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public T find(Class<T> clazz, String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		T t = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			
			//获取結果集中所有列的列名
			String[] colNames = this.getColumnNames(rs);
			
			//获取指定类的所有setter方法
			Map<String, Method> setters = this.getSetters(clazz);
			
			String methodName = null;
			Class<?>[] types = null;
			Class<?> type = null;
			Method method = null;
			if(rs.next()) {
				t = clazz.newInstance();
				
				//循环获取每一个列的值注入到封应的对象属性中
				for (String colName : colNames) {
					methodName = "set" + colName;
					method = setters.get(methodName); // 通过方法名获取对应的方法
					
					if (method == null) {
						continue;
					}
					
					types = method.getParameterTypes(); // 如果不为空，则获取形参列表
			
					if (types == null) {
						continue;
					}
					
					type = types[0]; // 取第一个形参
					if (Integer.TYPE == type || Integer.class == type) {
						method.invoke(t, rs.getInt(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else {
						method.invoke(t, String.valueOf(rs.getObject(colName)));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return t;
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> finds(Class<T> clazz, String sql, List<Object> params) {
		List<T> list = new ArrayList<T>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement (sql); // 预编译语句
			this.setParams(pstmt, params); //给预编译语句中的占位符赋值
			
			rs = pstmt.executeQuery(); //执行查询
			
			//如果获取结果集中列的类名->取到列名后我们存到-个数组中，便于后面的循环取值- >如何确定数组的大小?
			String[] colNames = this.getColumnNames(rs);
			
			// Map<String, Method> setters = getSetters(clazz); //获取指定对象的setter方法
			//获取给定类中的setter方法
			
			//从中提取出setter方法，已方法名为键，对应的方法为值，注意:我这里会将所有的方法名全部转成小写字母的方法
			Map<String, Method> setters = getSetters(clazz);
			
			String methodName = null;
			T t;
			Method method = null ;
			Class<?>[] types = null;
			Class<?> type = null;
			while (rs.next()) { // 每次循环得到- -行数据，-行数据对应一个实体类对象
				t = clazz.newInstance(); // 注意:此时需要调用类的无参构成
				
				for (String colName : colNames) { // 将这个列的值注 入到对应的对象的属性中，所有需要找到对应的setter方法
					methodName = "set" + colName; // setsid
					
					// 从setter方法找到对应的方法
					method = setters.get(methodName);
					if (method == null) {
						continue; // 直接执行下一次循环
					}
					
					//获取这个方法的参数列表，考虑到正常的setter方法只会有一-个参数，所以我们只取第一个参数的类型
					types = method.getParameterTypes();
					if (types == null) { //说明没有形参
						continue ;
					}
					
					type = types[0]; // 获取第一个形參的类型
					
					if (Integer.TYPE == type || Integer.class == type) {
						method.invoke(t, rs.getInt(colName)); // 反向激活这个方法， 相当于t.setsid(value)
					} else if (Double.TYPE == type || Double.class == type) {
						method.invoke(t, rs.getDouble(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else {
						method.invoke(t, String.valueOf(rs.getObject(colName)));
					}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e .printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}
	
	
	/**
	 * 查询单个对象
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public T find(Class<T> clazz, String sql, List<Object> params) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		T t = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement (sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			
			//获取结果集中 所有列的列名
			String[] colNames = this.getColumnNames(rs);
			
			//获取指定类的所有setter方法
			Map<String, Method> setters = this.getSetters(clazz);
			
			String methodName = null;
			Class<?>[] types = null;
			Class<?> type = null;
			Method method = null;
			if(rs.next()) {
				t = clazz.newInstance();
				
				//循环获取每一 个列的值注入到对应的对象属性中
				for (String colName : colNames) {
					methodName = "set" + colName;
					method = setters.get(methodName); // 通过方法名获取对应的方法
					
					if (method == null) {
						continue;
					}
					
					types = method.getParameterTypes(); //如果不为空，则获取形参列表
					
					if (types == null) {
						continue;
					}
					
					type = types[0]; // 取第一个形参
					
					if (Integer.TYPE == type || Integer.class == type) {
						method.invoke(t, rs.getInt(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else if (Float.TYPE == type || Float.class == type) {
						method.invoke(t, rs.getFloat(colName));
					} else {
						method.invoke(t, String.valueOf(rs.getObject(colName)));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return t;
	}
}
