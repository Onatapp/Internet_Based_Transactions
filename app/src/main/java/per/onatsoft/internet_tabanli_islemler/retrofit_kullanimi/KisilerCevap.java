package per.onatsoft.internet_tabanli_islemler.retrofit_kullanimi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KisilerCevap { //* Retrofit kurulumu aşamasının ilk parçalarından

    @SerializedName("kisiler")
    @Expose
    private List<Kisiler> kisiler;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Kisiler> getKisiler() {
        return kisiler;
    }

    public void setKisiler(List<Kisiler> kisiler) {
        this.kisiler = kisiler;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}