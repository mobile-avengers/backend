package mobile.avengers.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.sql.Timestamp

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "condition")
    var condition: String,

    @Column(name = "create_date")
    var createDate: Timestamp,  // TODO дорматировать во view  красивую строку формата "hh:mm DD.MMY.YYYY"

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @OneToMany(mappedBy = "order")
    var productsInOrder: MutableList<OrderProduct> = mutableListOf(),

    var cost: Float = 0f  // TODO подсчитывать как-то и возвращать во view

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != other.id) return false
        if (condition != other.condition) return false
        if (createDate != other.createDate) return false
        if (user != other.user) return false
        if (cost != other.cost) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + createDate.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + cost.hashCode()
        return result
    }
}
