package online.digital.wallet.eWallet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.Wallet;
import online.digital.wallet.eWallet.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletService walletService;

    public User createUser(User user) {
        userRepository.save(user); // parent should be saved first

        Wallet wallet = walletService.createWallet(user);

        user.setWallet(wallet);

        User savedUser = userRepository.save(user);

        return savedUser;
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
