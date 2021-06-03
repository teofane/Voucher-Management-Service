import java.util.ArrayList;

public class UserVoucherMap<K, V> extends ArrayMap<Integer, ArrayList<Voucher>>{
    ArrayList<Voucher> listCampaignVoucher = new ArrayList<>();

    boolean addVoucher(Voucher v) {
        if(super.containsKey(v.idCampaign)) {
            super.get(v.idCampaign).add(v);
        }
        else {
            listCampaignVoucher.add(v);
            put(v.idCampaign, listCampaignVoucher);
        }
        return true;
    }
}
