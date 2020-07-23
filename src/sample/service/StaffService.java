package sample.service;

import sample.Staff;

import java.io.IOException;
import java.util.List;

public interface StaffService {

    public  void saveStaff(Staff staff) throws IOException;

    List<Staff> getAllStaff() throws IOException;

    Staff getStaffByID(Integer ID) throws IOException;

    public void deleteStaff (Integer ID) throws IOException;
}
