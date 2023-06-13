package services.on.servicesontap.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterWorkerModal
{
    @SerializedName("Result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
