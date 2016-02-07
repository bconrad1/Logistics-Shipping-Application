public interface Facility {

    String getName();
    int getCost();
    int getProductPerDay();
    String getFacilityInfo();

    void setName(String name);
    void setCost(int cost);
    void setProductPD(int productPerDay);
}