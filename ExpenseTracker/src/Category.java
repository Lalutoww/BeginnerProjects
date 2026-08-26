import java.util.Optional;

public enum Category {
    FOOD,
    TRANSPORT,
    HOUSING,
    HEALTH,
    ENTERTAINMENT,
    SHOPPING,
    EDUCATION,
    OTHER;

    public static Optional<Category> fromNumber(int number) {
        if (number < 1 || number > values().length) {
            return Optional.empty();
        }
        return Optional.of(values()[number - 1]);
    }
}
