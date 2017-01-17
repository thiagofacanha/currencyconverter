
package br.com.sandclan.currencyquotation.Entities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CurrencyConverter {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     * 
     * @return
     *     The base
     */
    public String getBase() {
        return base;
    }

    /**
     * 
     * @param base
     *     The base
     */
    public void setBase(String base) {
        this.base = base;
    }

    public CurrencyConverter withBase(String base) {
        this.base = base;
        return this;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public CurrencyConverter withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * 
     * @return
     *     The rates
     */
    public Rates getRates() {
        return rates;
    }

    /**
     * 
     * @param rates
     *     The rates
     */
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public CurrencyConverter withRates(Rates rates) {
        this.rates = rates;
        return this;
    }

}
