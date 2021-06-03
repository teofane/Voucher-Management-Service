import java.util.Date;
import java.util.UUID;

public abstract class Voucher {
    int idVoucher;
    UUID codeVoucher = UUID.randomUUID();
    VoucherStatusType statusVoucher;
    Date usedDate;
    String emailAddress;
    int idCampaign;

    public Voucher(int idVoucher, UUID codeVoucher, VoucherStatusType statusVoucher,
                   String emailAddress, int idCampaign) {

        this.idVoucher = idVoucher;
        this.codeVoucher = codeVoucher;
        this.statusVoucher = statusVoucher;
        this.emailAddress = emailAddress;
        this.idCampaign = idCampaign;
    }
}