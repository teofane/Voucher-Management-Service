import java.util.ArrayList;

public class VMS {
    //Singleton design pattern
    ArrayList<Campaign> campaigns = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    private static VMS instance;

    private VMS() {}

    public static VMS getInstance() {
        if(instance == null) {
            instance = new VMS();
        }
        return instance;
    }

    public ArrayList<Campaign> getCampaigns(){
    return campaigns;
    }

    public Campaign getCampaign(Integer id) {
        for(Campaign a : campaigns) {
            if(a.campaignId.equals(id))
                return a;
        }
        return null;
    }

    public void addCampaign(Campaign campaign) {
        this.campaigns.add(campaign);
    }

    public void updateCampaign(Integer id, Campaign campaign) {
        Campaign a_new;
        for(Campaign a : campaigns) {
            if(a.campaignId.equals(id) && a.campaignStatus.equals(CampaignStatusType.NEW)) {
                a_new = a;
                a_new.campaignStatus =  campaign.campaignStatus;
                a_new.campaignId = campaign.campaignId;
                a_new.campaignName = campaign.campaignName;
                a_new.campaignDescription = campaign.campaignDescription;
                a_new.nrTotalVouchers = campaign.nrTotalVouchers;
                a_new.finalDate = campaign.finalDate;
                a_new.startDate = campaign.startDate;
                a_new.UserDictionary = campaign.UserDictionary;

                campaigns.set(id, a_new);
            }

            if(a.campaignId.equals(id) && a.campaignStatus.equals(CampaignStatusType.STARTED)) {
                a_new = a;

                if(campaign.nrTotalVouchers >= (a.nrTotalVouchers-a.nrAvailableVouchers)) {
                    a_new.nrAvailableVouchers = a.nrAvailableVouchers + (campaign.nrTotalVouchers - a.nrTotalVouchers);
                    a_new.nrTotalVouchers = campaign.nrTotalVouchers;
                }

                a_new.finalDate = campaign.finalDate;
                campaigns.set(id, a_new);
            }
        }
        campaign.update();
    }

    public void cancelCampaign(Integer id) {
        for(Campaign c : campaigns) {
            if(c.campaignId.equals(id) && (c.campaignStatus.equals(CampaignStatusType.NEW) || c.campaignStatus.equals(CampaignStatusType.STARTED))) {

                c.campaignStatus = CampaignStatusType.CANCELLED;
                campaigns.set(id, c);
                c.update();
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}