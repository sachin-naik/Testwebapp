package RestModels;

import java.util.List;

public class CityModel {
	private List<String> cities;
	private int numberOfCities;

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public int getNumberOfCities() {
		return numberOfCities;
	}

	public void setNumberOfCities(int numberOfCities) {
		this.numberOfCities = numberOfCities;
	}

}
