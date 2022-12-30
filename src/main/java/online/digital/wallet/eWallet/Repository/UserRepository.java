package online.digital.wallet.eWallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

import online.digital.wallet.eWallet.Model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
   User findByEmail(String email);

   // @Modifying
   // @Query("update User u set u.name = :#{#user.name}, " +
   //          "u.mobileNo = :#{#user.mobileNo}, " +
   //          "where u.id = :#{#user.id}")
   // int updateUserDetails(User user);
}
