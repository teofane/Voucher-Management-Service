import java.util.ArrayList;

public class CampaignVoucherMap<K, V> extends ArrayMap<String, ArrayList<Voucher>> {

    boolean addVoucher(Voucher v) {
        ArrayList<Voucher> listEmailVoucher = new ArrayList<>();

        if(super.containsKey(v.emailAddress)) // if the user already have a list with at least one voucher
            super.get(v.emailAddress).add(v);
        else
        {
            listEmailVoucher.add(v);
            put(v.emailAddress, listEmailVoucher);
        }

        return true;
    }
}
