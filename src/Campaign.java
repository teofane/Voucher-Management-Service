import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Campaign {
    Integer campaignId;
    String campaignName;
    String campaignDescription;
    LocalDateTime startDate;
    LocalDateTime finalDate;
    int nrTotalVouchers;
    int nrAvailableVouchers;
    CampaignStatusType campaignStatus;
    ArrayList<Voucher> VouchersDictionary = new ArrayList<>();
    ArrayList<User> UserDictionary = new ArrayList<>();
    ArrayList<User> observers = new ArrayList<>();

    public Campaign(int campaignId, String campaignName, String campaignDescription,
                    LocalDateTime startDate, LocalDateTime finalDate, int nrTotalVouchers) {
    this.campaignId = campaignId;
    this.campaignName = campaignName;
    this.campaignDescription = campaignDescription;
    this.startDate = startDate;
    this.finalDate = finalDate;
    this.nrTotalVouchers = nrTotalVouchers;
}

    public ArrayList<Voucher> getVouchers() {
        return VouchersDictionary;
    }

    public Voucher getVoucher(String code){
        for( int i = 0; i < nrTotalVouchers; i++ ) {
            if(code.equals(VouchersDictionary.get(i).codeVoucher.toString())) {
                    return VouchersDictionary.get(i);
            }
        }
        return null;
    }

    public Voucher generateVoucher(String email, String voucherType, float value) {
        Voucher v = null;
        UUID codeVoucher = UUID.randomUUID();
        for(int i = 0; i < UserDictionary.size(); i++) {
            if(UserDictionary.get(i).userEmailAddress.equals(email)) {
                if(nrAvailableVouchers > 0) {
                    int idVoucher = 1+nrTotalVouchers-nrAvailableVouchers;// it will count from 1
                    float discount = value; // if we use the variable for LoyalityVoucher
                    float disposableSum = value;// if we use the variable for GiftVoucher
                    int idCampaign = this.campaignId;
                    VoucherStatusType statusVoucher = VoucherStatusType.UNUSED;

                    if (voucherType.equals("LoyalityVoucher")) {
                        v = new LoyalityVoucher(idVoucher, codeVoucher, statusVoucher,
                                email, idCampaign, discount);
                    }

                    if(voucherType.equals("GiftVoucher")) {
                        v = new GiftVoucher(idVoucher, codeVoucher, statusVoucher,
                                email, idCampaign, disposableSum);
                    }

                    nrAvailableVouchers--;
                    VouchersDictionary.add(v);

                    for(User u : UserDictionary) {
                        if(u.userEmailAddress.equals(email)) {
                            u.receivedVouchers.add(v);
                            addObserver(u);
                        }
                    }
                }
            }
        }
        return v;
    }

    public void redeemVoucher(String code, LocalDateTime date) {
        for(Voucher v : VouchersDictionary) {
             if(v.codeVoucher.toString().equals(code) && startDate.compareTo(date) <=1 && finalDate.compareTo(date) >= 1
             && campaignStatus.equals(CampaignStatusType.CANCELLED) == false && v.statusVoucher.equals(VoucherStatusType.UNUSED)) {

                v.statusVoucher = VoucherStatusType.USED;
             }
        }
    }

    public ArrayList<User> getObservers() {
        return observers;
    }

    public void addObserver(User user) {
        observers.add(user);
    }

    public void removeObserver(User user) {
        observers.remove(user.userId);
    }

    public void notifyAllObservers(Notification notification) {
        for(User u : observers) {
            u.notificationsCollection.add(notification);
        }
    }

    public void update() {
        Notification notification;

        for(User u : observers) {
            LocalDateTime Date = LocalDateTime.now();
            ArrayList<String> vouchersCodes = new ArrayList<>();

            for(Voucher v : u.receivedVouchers) {
                vouchersCodes.add(v.codeVoucher.toString());
            }

            if(campaignStatus.equals(CampaignStatusType.CANCELLED))
                notification = new Notification(NotificationType.CANCEL, campaignId, Date, vouchersCodes);
            else
                notification = new Notification(NotificationType.EDIT, campaignId, Date, vouchersCodes);

            u.notificationsCollection.add(notification);
        }
    }
}
