import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class User {
    int userId;
    String userName;
    String userEmailAddress;
    String userPassword;
    UserType userType;
    ArrayList<Voucher> receivedVouchers = new ArrayList<Voucher>();
    Collection<Notification> notificationsCollection = new Collection<Notification>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Notification> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Notification notification) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Notification> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };

    public User(int userId, String userName, String userEmailAddress,
                String userPassword, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmailAddress = userEmailAddress;
        this.userPassword = userPassword;
        this.userType = UserType.valueOf(userType);
    }
}
