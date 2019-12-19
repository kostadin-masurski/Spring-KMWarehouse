package km.kmwarehouse.services.services.implementations.validation;

import km.kmwarehouse.data.repositories.UsersRepository;
import km.kmwarehouse.services.models.RegisterUserServiceModel;
import km.kmwarehouse.services.services.AuthValidationService;
import org.springframework.stereotype.Service;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    private final UsersRepository usersRepository;

    public AuthValidationServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isEmailValid(user.getEmail()) &&
                this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isUsernameFree(user.getUsername());
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameFree(String username) {
        return !usersRepository.existsByUsername(username);
    }

    private boolean isEmailValid(String email) {
        return true;
    }
}
