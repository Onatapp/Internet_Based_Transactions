package per.onatsoft.internet_tabanli_islemler.retrofit_kullanimi;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import per.onatsoft.internet_tabanli_islemler.R;
import per.onatsoft.internet_tabanli_islemler.databinding.ActivityRetrofitBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    private ActivityRetrofitBinding bind;
    private KisilerInterface kisiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        bind = ActivityRetrofitBinding.inflate(getLayoutInflater()); //* VIEW Binding kullanımı
        setContentView(bind.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        kisiInterface = ApiUtils.getKisiInterface(); //* Bu kod satırı Retrofit CRUD methodlarından önce yazılıyor.
        kisiGuncelleRetrofit();
    }

    public void kisiListeleRetrofit() { //-- RETROFIT kullanarak web servisden verileri çekerek listeleme işlemi. GET İsteği

        kisiInterface.tumKisiler().enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {

                List<Kisiler> kisilerListe = response.body().getKisiler();

                for (Kisiler k : kisilerListe) {

                    Log.e("*************", "*************");
                    Log.e("KİŞİ ID", k.getKisiId());
                    Log.e("KİŞİ AD", k.getKisiAd());
                    Log.e("KİŞİ TELEFON", k.getKisiTel());
                }
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {
                Log.e("R:TÜM KİŞİLERİ LİSTELEME HATASI", t.getMessage());
            }
        });
    }

    public void kisiAraRetrofit(){ //-- RETROFIT kullanarak web servisden verileri çekerek arama işlemi. POST İsteği

        kisiInterface.kisiArama("R").enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {

                List<Kisiler> kisiListe = response.body().getKisiler();

                for (Kisiler k : kisiListe){

                    Log.e("*************", "*************");
                    Log.e("KİŞİ ID", k.getKisiId());
                    Log.e("KİŞİ AD", k.getKisiAd());
                    Log.e("KİŞİ TELEFON", k.getKisiTel());
                }
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {
                Log.e("R:KİŞİ ARAMA HATASI", t.getMessage().toString());
            }
        });
    }

    public void kisiSilRetrofit(){ //-- POST İsteği

        kisiInterface.kisiSil(5).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                Log.e("SİLME BAŞARILI", response.body().getSuccess().toString());
                Log.e("SİLME YANIT MESAJI", response.body().getMessage().toString());
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
                Log.e("R:SİLME HATASI", t.getMessage().toString());
            }
        });
    }

    public void kisiEkleRetrofit(){ //-- POST İsteği

        kisiInterface.kisiEkle("Arda", "5525834933").enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                Log.e("SİLME BAŞARILI", response.body().getSuccess().toString());
                Log.e("SİLME YANIT MESAJI", response.body().getMessage().toString());
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
                Log.e("R:EKLEME HATASI", t.getMessage().toString());
            }
        });
    }

    public void kisiGuncelleRetrofit(){ //-- POST İsteği

        kisiInterface.kisiGuncelle(6, "Onat", "5537220000").enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                Log.e("SİLME BAŞARILI", response.body().getSuccess().toString());
                Log.e("SİLME YANIT MESAJI", response.body().getMessage().toString());
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
                Log.e("R:GÜNCELLEME HATASI", t.getMessage().toString());
            }
        });
    }
}