package per.onatsoft.internet_tabanli_islemler;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import per.onatsoft.internet_tabanli_islemler.databinding.ActivityPicassoBinding;

public class PicassoActivity extends AppCompatActivity {

    private ActivityPicassoBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        bind = ActivityPicassoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //** Picasso kütüphanesi kullanımları
        bind.btnLocal.setOnClickListener(view -> {

            Picasso.get()
                    .load(R.drawable.mobil_destek_logo)
                    .resize(500,500)
                    .into(bind.imgResim);
        });

        bind.imgBtnRefresh.setOnClickListener(view -> {

            Picasso.get()
                    .load(R.drawable.round_build_24)
                    .into(bind.imgResim);
        });

        bind.btnInternet1.setOnClickListener(view -> {

            String url = "http://kasimadalan.pe.hu/filmler/resimler/inception.png";
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.round_android_24)
                    .resize(500, 800)
                    .into(bind.imgResim);
        });

        bind.btnInternet2.setOnClickListener(view -> {

            String url = "https://play-lh.googleusercontent.com/_BkINfOKVA8Rt0YdZphAyRbRD_zhqydUQ5uDwBn8a18zfu0a0H-lS9BXGQuz-8T4Nw=w240-h480-rw";
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.round_android_24)
                    .resize(500,500)
                    .into(bind.imgResim);
        });

    }
}