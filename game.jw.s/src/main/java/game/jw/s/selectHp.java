package game.jw.s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class selectHp<T> {
//	ArrayList<Person> students = new ArrayList<Person>();
	public String UserHp(int idx) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SQLiteConfig config = new SQLiteConfig();
		String text = "";
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/c:\\tomcat\\Game.sqlite", config.toProperties());
			String query = "SELECT * FROM userinfo WHERE idx=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query, idx);
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				text = resultSet.getString(idx);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return text;
}
}
