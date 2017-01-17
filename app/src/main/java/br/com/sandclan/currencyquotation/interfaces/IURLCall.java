package br.com.sandclan.currencyquotation.interfaces;

import br.com.sandclan.currencyquotation.Entities.CurrencyConverter;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by LordElfo on 15/07/2016.
 */
public interface IURLCall {

    @GET("/latest")
    void getCurrencies(@Query("base") String currency, Callback<CurrencyConverter> response);
}
