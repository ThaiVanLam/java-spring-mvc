package vn.hoidanit.laptopshop.service;

import java.util.List;

import vn.hoidanit.laptopshop.domain.Role;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public String handleHello() {
        return "hello from service";
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        return eric;
    }

    public User getUserById(long id) {
        return this.userRepository.findFirstById(id);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    // write me function to convert registerDTO to User
    public User convertRegisterDTOToUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        return user;
    }

    public boolean checkEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
