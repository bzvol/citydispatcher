package hu.kisspd.citydp.model;

public enum CityType {
    CITY("Nagyváros", 29, VehicleType.values()),
    TOWN("Kisváros", 23,
            new VehicleType[]{VehicleType.BUS, VehicleType.TRAM, VehicleType.TROLLEY, VehicleType.TRAIN}),
    VILLAGE("Falu", 17, new VehicleType[]{VehicleType.BUS});

    private final String displayName;
    private final int size;
    private final VehicleType[] availableVehicleTypes;

    CityType(String displayName, int size, VehicleType[] availableVehicleTypes) {
        this.displayName = displayName;
        this.size = size;
        this.availableVehicleTypes = availableVehicleTypes;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getSize() {
        return size;
    }

    public int getRadius () {
        return size / 2;
    }

    public VehicleType[] getAvailableVehicleTypes() {
        return availableVehicleTypes;
    }

    public static CityType fromName(String name) {
        for (CityType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
