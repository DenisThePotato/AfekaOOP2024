package DenisMogilevsky;

public class WrappableProduct extends Product{
    double specialPackageCost = 0;
    boolean bWrapped = false;

    WrappableProduct(){}

    WrappableProduct(WrappableProduct wrappableProduct){
        super(wrappableProduct);
        this.specialPackageCost = wrappableProduct.specialPackageCost;
        this.bWrapped = wrappableProduct.bWrapped;
    }

    public WrappableProduct deepClone(){
        return new WrappableProduct(this);
    }

    WrappableProduct(Product product){
        super(product);
    }

    public void wrapProduct(){
        if(this.bWrapped){
            return;
        }
        this.price += specialPackageCost;
        this.bWrapped = true;
    }

    public void unWrapProduct(){
        if(!this.bWrapped){
            return;
        }
        this.price -= specialPackageCost;
        this.bWrapped = false;
    }

    @Override
    public String toString() {
        if(this.bWrapped){
            return(name + ". Price: " + price + "$ (includes " + specialPackageCost +
                    "$ for special packaging). Category: " + category.toString() + ". SN: " + serialNumber + ".");
        }
        return(name + ". Price: " + price + "$. Category: " + category.toString() + ". SN: " + serialNumber + ".");
    }

    public void setSpecialPackageCost(double specialPackageCost) throws IllegalArgumentException {
        if(specialPackageCost < 0){
            throw new IllegalArgumentException("Invalid special package cost.");
        }
        if(this.bWrapped){  // adjusts wrapped products price according to the new wrapping cost
            this.unWrapProduct();
            this.specialPackageCost = specialPackageCost;
            this.wrapProduct();
            return;
        }
        this.specialPackageCost = specialPackageCost;
    }

    public double getSpecialPackageCost() {
        return specialPackageCost;
    }

    public boolean isWrapped() {
        return bWrapped;
    }
}
