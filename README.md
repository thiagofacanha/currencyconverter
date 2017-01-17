# currencyconverter
A currency converter made as study of using Fixer.io

In this solution I tried use JSONObject and JSONArray doing the URL connection manually
I was able to access and receive the result but I had problem when was trying transform the rates currencies to a JSONArray. Wasnt able to store them because of the diferent keys(The currency code) so I used retrofit http://square.github.io/retrofit/) and GSON(https://github.com/google/gson) to access the values and was really more easy. I used the site (http://www.jsonschema2pojo.org/) to generate my JSON entities.

I'm doing some input validations like internet connection, empty values and conversion of the same currency.

I'm using values stored in strings.xml file to help in case of internationalization needs and im using too values stored in dimens.xml to help in case of multiple screens as target.

Permissions Needed
android.permission.ACCESS_NETWORK_STATE --> To check if user has internet access
android.permission.INTERNET --> To access the API
