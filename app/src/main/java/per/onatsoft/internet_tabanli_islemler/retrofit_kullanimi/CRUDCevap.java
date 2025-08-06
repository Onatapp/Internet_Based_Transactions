package per.onatsoft.internet_tabanli_islemler.retrofit_kullanimi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CRUDCevap { //* Retrofit kurulumu aşamasının ilk parçalarından

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}