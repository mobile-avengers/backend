package mobile.avengers.backend.enums

enum class OrderConditions(val status: String) {
    NEW("new"),
    CANCELED("canceled"),
    CONFIRMED("confirmed"),
    IN_DELIVERY("inDlivery"),
    FINISHED("finished"),
}
