package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.LoginUserServiceModel;
import km.kmwarehouse.services.models.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}
