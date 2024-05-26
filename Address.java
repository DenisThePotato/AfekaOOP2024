public class Address {
    private String streetName;
    private int buildingNumber;
    private String cityName;
    private String countryName;

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    @Override
    public String toString(){
        return(streetName + " " + buildingNumber + ", " + cityName + ", " + countryName + ".");
    }
}
