package online.digital.wallet.eWallet.Model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String fromUser;
    private String toUser;
    private double amount;

    @Override
    public String toString() {
        return "PaymentDto [fromUser=" + fromUser + ", toUser=" + toUser + ", amount=" + amount + "]";
    }
}
