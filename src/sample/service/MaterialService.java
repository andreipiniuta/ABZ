package sample.service;

import sample.Material;

import java.io.IOException;
import java.util.List;
//методы теже что в DAO кроме update, щр бндет на сервере в уровне Service
public interface MaterialService {

    public  void saveMaterial(Material material) throws IOException;

    List<Material> getAllMaterial() throws IOException;

    Material getMaterialByID(Integer ID) throws IOException;

    public void deleteMaterial (Integer ID) throws IOException;

}
