package by.bsuir.phoneshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class PriceHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal price;
    private String date;
    private Currency currency;
    public PriceHistory() {

    }
    public PriceHistory(Date date, BigDecimal price, Currency currency) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        this.date = formatter.format(date);
        this.price = price;
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        this.date = formatter.format(date);
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
