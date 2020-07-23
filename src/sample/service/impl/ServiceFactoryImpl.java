package sample.service.impl;

import sample.service.*;
//единственная конкретная фабрика для Service которая возвращвет объекты других классов

public class ServiceFactoryImpl extends ServiceFactory {

//переопределяем методы абстрактного класса
    @Override
    public MaterialService getMaterialService() {//возвращвет объекты по типу интерфейса
        return new MaterialServiceImpl();//возвращает объект по типу Material
    }

    @Override
    public RecipeService getRecipeService() {//возвращвет объекты по типу интерфейса
        return new RecipeServiceImpl();//возвращает объект по типу Recipe
    }

    @Override
    public ProductService getProductService() {
        return new ProductServiceImpl();//возвращает объект по типу Product
    }

    @Override
    public StaffService getStaffService() {
        return new StaffServiceImpl();//возвращает объект по типу Staff
    }

    @Override
    public TruckService getTruckService() {
        return new TruckServiceImpl();//возвращает объект по типу Truck
    }

}
