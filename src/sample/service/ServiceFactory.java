package sample.service;


import sample.Recipe;
import sample.service.impl.ServiceFactoryImpl;

public abstract class ServiceFactory {

    public abstract MaterialService getMaterialService(); // абстрактные методы по интерфайсам

    public abstract RecipeService getRecipeService();// абстрактные методы по интерфайсам

    public abstract ProductService getProductService();

    public abstract StaffService getStaffService();

    public abstract TruckService getTruckService();

    public static ServiceFactory getServiceFactory() {// метод, кот возвращает конкретную фабрику(для Service она одна)
        return new ServiceFactoryImpl();
    }
}
