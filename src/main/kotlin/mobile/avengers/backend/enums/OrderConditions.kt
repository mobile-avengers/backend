package mobile.avengers.backend.enums

enum class OrderConditions(val status: String) {
    NEW("new"),
    CANCELED("canceled"),
    PAYED("payed"),
    CONFIRMED("confirmed"),
    BOUGHT_IN_SHOP("boughtInShop"), // выкуплен
    SENT("sent"), // отправлен
    IN_DELIVERY("inDlivery"),
    CLIENT_RECEIVED("clientReceived"),
}
