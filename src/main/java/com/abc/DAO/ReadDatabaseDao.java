package com.abc.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import RestModels.CityModel;

public class ReadDatabaseDao {
	public static CityModel readDataFromDb() {
		Connection connection = MySQLConnectionUtil.getConnection();
		CityModel responseCityModel = null;
		try {
			Statement statement = connection.createStatement();
			// Result set get the result of the SQL query
			ResultSet resultSet = statement.executeQuery("select Name from city where Population > 500000 limit 20");
			responseCityModel = getData(resultSet);
			MySQLConnectionUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCityModel;

	}

	private static CityModel getData(ResultSet resultSet) throws SQLException {
		int count = 0;
		List<String> list = new ArrayList<String>();
		while(resultSet.next()) {
			String cityName = resultSet.getString("Name");
			list.add(cityName);
			System.out.println("cityName: "+cityName);
			count ++;
		}
		CityModel responseCityModel = new CityModel();
		responseCityModel.setCities(list);
		responseCityModel.setNumberOfCities(count);
		return responseCityModel;
		
	}
}
