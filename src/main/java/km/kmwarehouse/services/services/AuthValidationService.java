package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel user);
}
