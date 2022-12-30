package online.digital.wallet.eWallet.Model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninDto {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "SigninDto [email=" + email + ", password=" + password + "]";
    }
}

