package per.onatsoft.internet_tabanli_islemler.retrofit_kullanimi;

public class ApiUtils { //* Retrofit kurulumu aşaması parçası

    public static final String BaseURL = "https://restfuldb.onatsomer.com";

    public static KisilerInterface getKisiInterface(){
        return RetrofitClient.getClient(BaseURL).create(KisilerInterface.class);
    }
}