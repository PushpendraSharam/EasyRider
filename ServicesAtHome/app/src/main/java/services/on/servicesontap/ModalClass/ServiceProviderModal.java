package services.on.servicesontap.ModalClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ServiceProviderModal {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Min_Visit_Fee")
    @Expose
    private String minVisitFee;
    @SerializedName("Mobile_No")
    @Expose
    private String mobileNo;
    @SerializedName("Experience")
    @Expose
    private String experience;
    @SerializedName("Max_Visit_Fee")
    @Expose
    private String maxVisitFee;
    @SerializedName("Pin_Code")
    @Expose
    private String pinCode;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Skill")
    @Expose
    private String skill;
    @SerializedName("Unique_Code")
    @Expose
    private String uniqueCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinVisitFee() {
        return minVisitFee;
    }

    public void setMinVisitFee(String minVisitFee) {
        this.minVisitFee = minVisitFee;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMaxVisitFee() {
        return maxVisitFee;
    }

    public void setMaxVisitFee(String maxVisitFee) {
        this.maxVisitFee = maxVisitFee;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}