package per.onatsoft.internet_tabanli_islemler;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import per.onatsoft.internet_tabanli_islemler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //-- View Binding başlatma
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //** Uygulama da çalıştırılacak Volley Methodu: Ekleme, Güncelleme, Silme, Listeleme, Select
        kisiListeleVolley();
    }

    public void kisiEkleVolley() {
        //** VOLLEY Kütüphanesinin Android uygulama da kaydetme işlemi.
        String url = "https://restfuldb.onatsomer.com/kisiler/insert_kisiler.php";  //--> Buraya çalışacak ilgili web servisin URL'si yazılıyor.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("KİŞİ EKLEME CEVAP", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //** VOLLEY Kütüphanesinde CRUD işlemleri bu methodda yazılıyor. Tabloda ki sütun ve değerleri String formatta "put" methodu ile alınıyor.

                Map<String, String> params = new HashMap<>();
                params.put("kisi_ad", "Onat Somer");
                params.put("kisi_tel", "8657649646");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    public void kisiGuncelleVolley() {

        String url = "https://restfuldb.onatsomer.com/kisiler/update_kisiler.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("KİŞİ GÜNCELLEME CEVAP", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("kisi_id", "5");
                params.put("kisi_ad", "ipek");
                params.put("kisi_tel", "2024001111");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }


    public void kisiSilVolley() {

        String url = "https://restfuldb.onatsomer.com/kisiler/delete_kisiler.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("KİŞİ SİLME CEVAP", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("kisi_id", "4");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    void kisiListeleVolley() {
        //** VOLLEY kütüphanesiyle web servis de olan kişileri listelerken başta Json türünde geldiği için sonrasında Json Parse yöntemiyle ayıklanıyor.
        String url = "https://restfuldb.onatsomer.com/kisiler/tum_kisiler.php";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("LİSTELEME JSON CEVAP", response);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kisiListe = jsonObject.getJSONArray("kisiler");

                    for (int i = 0; i < kisiListe.length(); i++) {

                        JSONObject k = kisiListe.getJSONObject(i);
                        int kisiID = k.getInt("kisi_id");
                        String kisiAd = k.getString("kisi_ad");
                        String kisiTel = k.getString("kisi_tel");

                        Log.e("KİŞİ ID", String.valueOf(kisiID));
                        Log.e("KİŞİ ADI", kisiAd);
                        Log.e("KİŞİ TELEFONU", kisiTel);
                        Log.e("***************", "***************");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Log.e("LİSTELEME HATA", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(request);
    }

    void kisiArama() {

        String url = "https://restfuldb.onatsomer.com/kisiler/tum_kisiler_arama.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("KİŞİ ARAMA CEVAP", response);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arananKisiler = jsonObject.getJSONArray("kisiler");

                    for (int i = 0; i < arananKisiler.length(); i++) {

                        JSONObject kisi = arananKisiler.getJSONObject(i);
                        int kisiID = kisi.getInt("kisi_id");
                        String kisiAd = kisi.getString("kisi_ad");
                        String kisiTel = kisi.getString("kisi_tel");

                        Log.e("KİŞİ ID", String.valueOf(kisiID));
                        Log.e("KİŞİ ADI", kisiAd);
                        Log.e("KİŞİ TELEFON", kisiTel);
                        Log.e("***************", "***************");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("KİŞİ ARAMA HATASI", error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("kisi_ad", "n");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}