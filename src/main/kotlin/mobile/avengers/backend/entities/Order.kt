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
)
