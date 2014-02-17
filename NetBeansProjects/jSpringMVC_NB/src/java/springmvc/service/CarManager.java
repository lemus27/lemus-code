package springmvc.service;

import java.util.List;

import springmvc.model.Car;

public class CarManager {
	
	private List<Car> carList;
	
	public List<Car> getCarList() {
		return carList;
	}	

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}
	
	public Car createCar(Car c) {
		Car car = new Car();
		car.setId((long)carList.size() + 1);
		car.setBrand(c.getBrand());
		car.setModel(c.getModel());
		car.setPrice(c.getPrice());
		
		carList.add(car);
		
		return car;
	}
}
