package br.com.sandclan.currencyquotation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.NumberFormat;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

import br.com.sandclan.currencyquotation.Entities.CurrencyConverter;
import br.com.sandclan.currencyquotation.interfaces.IURLCall;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QuotationScreen extends AppCompatActivity {

    private TextView txtVResult;
    private Spinner spnConverterFrom;
    private Spinner spnConverterTo;
    private TextView textVValueToConvert;
    private final String URL_QUOTATION = "http://api.fixer.io";
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_screen);

        txtVResult = (TextView) findViewById(R.id.textViewResult);
        spnConverterFrom = (Spinner) findViewById(R.id.spinnerConverterFrom);
        spnConverterTo = (Spinner) findViewById(R.id.spinnerConverterTo);
        textVValueToConvert = (TextView) findViewById(R.id.editTValueToConvert);
        fillSpinnersValues(spnConverterFrom, spnConverterTo);

    }


    public void buttonClicked(View view) {
        if ("=".equals(view.getTag())) {
            convertValue();
        } else if ("X".equals(view.getTag()) && textVValueToConvert.getText().length() > 0) {
            textVValueToConvert.setText(textVValueToConvert.getText().toString().substring(0, textVValueToConvert.getText().length() - 1));
        } else if (!"X".equals(view.getTag())) {
            textVValueToConvert.setText(textVValueToConvert.getText() + (String) view.getTag());
        }

    }

    /**
     * Fill spinners with currency types
     *
     * @param spinners
     */
    private void fillSpinnersValues(Spinner... spinners) {
        //Receiving the currency codes from a list in resources.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinners[0].setAdapter(adapter);
        spinners[1].setAdapter(adapter);
    }


    private void convertValue() {
        if (!isInternetConnected()) {
            showDialog(getString(R.string.quotation_screen_msg_no_network));
            return;
        }
        txtVResult.setText("");
        if (!isScreenValid()) {
            return;
        }
        callConverterAPI();
    }

    /**
     * Check if all fields are valid
     *
     * @return
     */
    private boolean isScreenValid() {
        if (textVValueToConvert.getText().toString().isEmpty()) {
            showDialog(getString(R.string.quotation_screen_msg_inser_value));
            return false;
        } else if (spnConverterFrom.getSelectedItem().toString().equals(spnConverterTo.getSelectedItem().toString())) {
            showDialog(getString(R.string.quotation_screen_msg_same_currency));
            return false;
        }
        return true;
    }

    /**
     * Call the Converter API
     */
    private void callConverterAPI() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL_QUOTATION).build();

        final IURLCall iurlCall = restAdapter.create(IURLCall.class);

        iurlCall.getCurrencies(spnConverterFrom.getSelectedItem().toString(), new Callback<CurrencyConverter>() {
            @Override
            public void success(CurrencyConverter currencyConverter, Response response) {
                float currencyResult;
                //TODO need improve this part of code. Many items in this switch. Would be better receive the
                //currencies code from the same source that spinners
                switch (spnConverterTo.getSelectedItem().toString()) {
                    case "AUD":
                        currencyResult = currencyConverter.getRates().getAUD();
                        break;
                    case "BGN":
                        currencyResult = currencyConverter.getRates().getBGN();
                        break;
                    case "BRL":
                        currencyResult = currencyConverter.getRates().getBRL();
                        break;
                    case "CAD":
                        currencyResult = currencyConverter.getRates().getCAD();
                        break;
                    case "CHF":
                        currencyResult = currencyConverter.getRates().getCHF();
                        break;
                    case "CNY":
                        currencyResult = currencyConverter.getRates().getCNY();
                        break;
                    case "CZK":
                        currencyResult = currencyConverter.getRates().getCZK();
                        break;
                    case "DKK":
                        currencyResult = currencyConverter.getRates().getDKK();
                        break;
                    case "EUR":
                        currencyResult = currencyConverter.getRates().getEUR();
                        break;
                    case "GBP":
                        currencyResult = currencyConverter.getRates().getGBP();
                        break;
                    case "HKD":
                        currencyResult = currencyConverter.getRates().getHKD();
                        break;
                    case "HRK":
                        currencyResult = currencyConverter.getRates().getHRK();
                        break;
                    case "HUF":
                        currencyResult = currencyConverter.getRates().getHUF();
                        break;
                    case "IDR":
                        currencyResult = currencyConverter.getRates().getIDR();
                        break;
                    case "ILS":
                        currencyResult = currencyConverter.getRates().getILS();
                        break;
                    case "INR":
                        currencyResult = currencyConverter.getRates().getINR();
                        break;
                    case "JPY":
                        currencyResult = currencyConverter.getRates().getJPY();
                        break;
                    case "KRW":
                        currencyResult = currencyConverter.getRates().getKRW();
                        break;
                    case "MXN":
                        currencyResult = currencyConverter.getRates().getMXN();
                        break;
                    case "MYR":
                        currencyResult = currencyConverter.getRates().getMYR();
                        break;
                    case "NOK":
                        currencyResult = currencyConverter.getRates().getNOK();
                        break;
                    case "NZD":
                        currencyResult = currencyConverter.getRates().getNZD();
                        break;
                    case "PHP":
                        currencyResult = currencyConverter.getRates().getPHP();
                        break;
                    case "PLN":
                        currencyResult = currencyConverter.getRates().getPLN();
                        break;
                    case "RON":
                        currencyResult = currencyConverter.getRates().getRON();
                        break;
                    case "RUB":
                        currencyResult = currencyConverter.getRates().getRUB();
                        break;
                    case "SEK":
                        currencyResult = currencyConverter.getRates().getSEK();
                        break;
                    case "SGD":
                        currencyResult = currencyConverter.getRates().getSGD();
                        break;
                    case "THB":
                        currencyResult = currencyConverter.getRates().getTHB();
                        break;
                    case "TRY":
                        currencyResult = currencyConverter.getRates().getTRY();
                        break;
                    case "USD":
                        currencyResult = currencyConverter.getRates().getUSD();
                        break;
                    case "ZAR":
                        currencyResult = currencyConverter.getRates().getZAR();
                        break;
                    default:
                        currencyResult = 0;
                }
                float result = Float.valueOf(textVValueToConvert.getText().toString()) * currencyResult;


                DecimalFormat df = new DecimalFormat("#.00");
                txtVResult.setText(df.format(result));
            }

            @Override
            public void failure(RetrofitError error) {
                showDialog(error.getMessage());
            }
        });
    }


    /**
     * shows a generic dialog with the message received
     *
     * @param msg
     */
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


    /**
     * returns if the device has internet
     *
     * @return
     */
    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() == null)
            return false;
        if (!connectivityManager.getActiveNetworkInfo().isConnected())
            return false;
        if (!connectivityManager.getActiveNetworkInfo().isAvailable())
            return false;
        return true;
    }

}
