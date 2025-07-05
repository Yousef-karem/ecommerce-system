package src.main.java.expiring;

import java.time.LocalDate;

public class ExpiresOnDate implements ExpirationBehavior{
    private LocalDate expirationDate;
    public ExpiresOnDate(LocalDate expirationDate)
    {
        this.expirationDate=expirationDate;
    }
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
