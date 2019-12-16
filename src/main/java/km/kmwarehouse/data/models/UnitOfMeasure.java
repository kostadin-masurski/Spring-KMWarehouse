package km.kmwarehouse.data.models;

public enum UnitOfMeasure {
    PC("PC"),
    KG("KG"),
    M("M"),
    L("L");

    private final String unit;

    UnitOfMeasure(String unit){
        this.unit = unit;
    }
}
