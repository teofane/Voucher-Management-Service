import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notification {
    NotificationType notificationType;
    LocalDateTime notificationDate;
    int idCampaign;
    ArrayList<String> codesVouchers = new ArrayList<String>();
    LocalDateTime now = LocalDateTime.now();

    public Notification(NotificationType notificationType, int idCampaign, LocalDateTime notificationDate, ArrayList<String> codeVoucher) {
        this.notificationDate = now;
        this.notificationType = notificationType;
        this.idCampaign = idCampaign;
        this.notificationDate = notificationDate;
        this.codesVouchers = codeVoucher;
    }
}
