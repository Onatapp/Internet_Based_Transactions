package per.onatsoft.internet_tabanli_islemler.retrofit_kullanimi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KisilerInterface { //* Retrofit kurulumu aşaması parçası
    //** Buraya yazılan web servislerin BaseURL'si ApiUtils sınıfından alınarak POST işlemi gerçekleşen
    // bir web servisin çalışacağı koşul sütunu burada parametre değeri olarak yazılıyor.
    // Daha sonra Activity sayfasında bu methodlar ve parametreler kullanılıyor.

    @GET("/kisiler/tum_kisiler.php")
    Call<KisilerCevap> tumKisiler();

    @POST("/kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    Call<KisilerCevap> kisiArama(@Field("kisi_ad") String kisiAd);

    @POST("/kisiler/delete_kisiler.php")
    @FormUrlEncoded
    Call<CRUDCevap> kisiSil(@Field("kisi_id") int kisiID);

    @POST("/kisiler/insert_kisiler.php")
    @FormUrlEncoded
    Call<CRUDCevap> kisiEkle(@Field("kisi_ad") String kisiAd,
                             @Field("kisi_tel") String kisiTel);

    @POST("/kisiler/update_kisiler.php")
    @FormUrlEncoded
    Call<CRUDCevap> kisiGuncelle(@Field("kisi_id") int kisiID,
                                 @Field("kisi_ad") String kisiAd,
                                 @Field("kisi_tel") String kisiTel);
}
