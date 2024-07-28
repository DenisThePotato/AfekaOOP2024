package DenisMogilevsky;

public class Address {
    private String streetName;
    private int buildingNumber;
    private String cityName;
    private String countryName;

    public Address(){}

    public Address(Address address){
        this.streetName = address.streetName;
        this.buildingNumber = address.buildingNumber;
        this.cityName = address.cityName;
        this.countryName = address.countryName;
    }

    public boolean setBuildingNumber(int buildingNumber) {
        if(buildingNumber > 0){
            this.buildingNumber = buildingNumber;
            return true;
        }
        return false;
    }

    public boolean setCityName(String cityName) {
        if(cityName != null){
            this.cityName = cityName;
            return true;
        }
        return false;
    }

    public boolean setCountryName(String countryName) {
        if(countryName != null){
            this.countryName = countryName;
            return true;
        }
        return false;
    }

    public boolean setStreetName(String streetName) {
        if(streetName != null){
            this.streetName = streetName;
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return(streetName + " " + buildingNumber + ", " + cityName + ", " + countryName + ".");
    }
}
