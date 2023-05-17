package mobile.avengers.backend.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.sql.Timestamp

@Entity
@Table(name = "products_in_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
data class OrderProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "condition")
    var condition: String,

    @Column(name = "price")
    var price: Float,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order

)
