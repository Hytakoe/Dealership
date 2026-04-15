package ru.cs.vsu.selyutinrv.model;

public enum BodyType {
    SEDAN("Седан"),
    HATCHBACK("Хэтчбек"),
    SUV("Внедорожник"),
    COUPE("Купе"),
    CONVERTIBLE("Кабриолет"),
    WAGON("Универсал"),
    MINIVAN("Минивэн"),
    PICKUP("Пикап"),
    VAN("Фургон"),
    LIFTBACK("Лифтбек");

    private final String displayName;

    BodyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}