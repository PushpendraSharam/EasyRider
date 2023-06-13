package services.on.servicesontap.ModalClass;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class FetchAllDataModal {

    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("ServiceProvider")
    @Expose
    private List<ServiceProviderModal> serviceProvider;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ServiceProviderModal> getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(List<ServiceProviderModal> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
