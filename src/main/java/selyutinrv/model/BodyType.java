package selyutinrv.model;

public enum BodyType {
    SEDAN("Седан"),
    HATCHBACK("Хэтчбек"),
    LIFTBACK("Лифтбек"),
    COUPE("Купе"),
    CONVERTIBLE("Кабриолет"),
    ROADSTER("Родстер"),
    SUV("Внедорожник"),
    CROSSOVER("Кроссовер"),
    WAGON("Универсал"),
    MINIVAN("Минивэн"),
    VAN("Фургон"),
    PICKUP("Пикап");

    private final String displayName;

    BodyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BodyType fromDisplayName(String displayName) {
        for (BodyType type : BodyType.values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип кузова: " + displayName);
    }

    @Override
    public String toString() {
        return displayName;
    }
}