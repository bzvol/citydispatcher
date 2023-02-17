package hu.kisspd.citydp.model;

public enum VehicleType {
    BUS("Busz", 50),
    TRAM("Villamos", 60),
    TROLLEY("Troli", 50),
    METRO("Metró", 80),
    TRAIN("Vonat", 0b1111000),
    FERRY("Komp", 20);

    private final String displayName;
    private final int speed;

    VehicleType(String displayName, int speed) {
        this.displayName = displayName;
        this.speed = speed;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getSpeed() {
        return speed;
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
