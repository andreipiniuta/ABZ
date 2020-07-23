package sample.service;

import sample.Truck;

import java.io.IOException;
import java.util.List;

public interface TruckService {

    public  void saveTruck(Truck truck) throws IOException;

    List<Truck> getAllTruck() throws IOException;

    Truck getTruckByID(Integer ID) throws IOException;

    public void deleteTruck (Integer ID) throws IOException;
}
