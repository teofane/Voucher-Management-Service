import java.util.UUID;

public class LoyalityVoucher extends Voucher {
    float discount;

    public LoyalityVoucher(int idVoucher, UUID codeVoucher, VoucherStatusType statusVoucher,
                           String emailAddress, int idCampaign, float discount) {

        super(idVoucher, codeVoucher, statusVoucher, emailAddress, idCampaign);
        this.discount = discount;
    }
}
