package km.kmwarehouse.services.services.implementations;

import km.kmwarehouse.data.models.User;
import km.kmwarehouse.data.repositories.UsersRepository;
import km.kmwarehouse.services.models.LoginUserServiceModel;
import km.kmwarehouse.services.models.RegisterUserServiceModel;
import km.kmwarehouse.services.services.AuthService;
import km.kmwarehouse.services.services.AuthValidationService;
import km.kmwarehouse.services.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService authValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;

    public AuthServiceImpl(
            AuthValidationService authValidationService,
            UsersRepository usersRepository,
            ModelMapper mapper,
            HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!authValidationService.isValid(model)) {
            throw new IllegalArgumentException("Invalid user");
        }

        User user = mapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception {
        String passwordHash = hashingService.hash(serviceModel.getPassword());
        return usersRepository.findByUsernameAndPassword(serviceModel.getUsername(), passwordHash)
                .map(user -> mapper.map(user, LoginUserServiceModel.class))
                .orElseThrow(() -> new Exception("Invalid user"));
    }
}
