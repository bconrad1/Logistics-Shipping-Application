public class FacilityImpl implements Facility {

    private String name;
    private int cost;
    private int productPD;



    FacilityImpl(String facName, int costPerDay, int productPerDay ) {

        setName(facName);
        setCost(costPerDay);
        setProductPD(productPerDay);


    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setProductPD(int productPerDay) {
        this.productPD = productPerDay;
    }

    public String getName(){
        return name;
    }

    public int getCost(){
        return cost;
    }

    public int getProductPerDay(){
        return productPD;
    }

    public String getFacilityInfo(){

        //Print out facility info. Format later
        return "TEMP";



    }

}