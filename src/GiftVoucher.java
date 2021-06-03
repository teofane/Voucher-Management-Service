import java.util.UUID;

public class GiftVoucher extends Voucher {
    float disposableSum;

    public GiftVoucher(int idVoucher, UUID codeVoucher, VoucherStatusType statusVoucher,
                       String emailAddress, int idCampaign, float disposableSum) {

        super(idVoucher, codeVoucher, statusVoucher, emailAddress, idCampaign);
        this.disposableSum = disposableSum;
    }
}
