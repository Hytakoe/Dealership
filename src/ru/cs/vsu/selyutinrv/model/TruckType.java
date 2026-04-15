package ru.cs.vsu.selyutinrv.model;

public enum TruckType {
    LIGHT("Легкий грузовик", 3.5),
    MEDIUM("Средний грузовик", 12.0),
    HEAVY("Тяжелый грузовик", 40.0),
    DUMP_TRUCK("Самосвал", 30.0),
    REFRIGERATOR("Рефрижератор", 20.0),
    TANKER("Автоцистерна", 25.0),
    FLATBED("Платформа", 25.0),
    TOW_TRUCK("Эвакуатор", 5.0);

    private final String displayName;
    private final double maxLoadCapacity;

    TruckType(String displayName, double maxLoadCapacity) {
        this.displayName = displayName;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    @Override
    public String toString() {
        return displayName;
    }
}