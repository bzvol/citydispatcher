package hu.kisspd.citydp.model;

public enum VehicleType {
    BUS("Busz"),
    TRAM("Villamos"),
    TROLLEY("Troli"),
    METRO("Metró"),
    TRAIN("Vonat"),
    FERRY("Komp");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleType fromName(String name) {
        if (name == null) {
            return null;
        }
        for (VehicleType v : VehicleType.values()) {
            if (name.equalsIgnoreCase(v.name())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
