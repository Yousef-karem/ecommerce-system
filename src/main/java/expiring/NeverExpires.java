package src.main.java.expiring;

public class NeverExpires implements ExpirationBehavior{
    @Override
    public boolean isExpired() {
        return false;
    }
}
