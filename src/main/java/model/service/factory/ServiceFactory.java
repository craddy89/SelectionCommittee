package model.service.factory;

import model.service.Service;

public class ServiceFactory {

    public static Service getService(ServiceType serviceType){
        return serviceType.getService();
    }
}
