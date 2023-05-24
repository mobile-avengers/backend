package mobile.avengers.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: Float,

    @Column(name = "link")
    var link: String,

    @Column(name = "size")
    var size: Int,

    @Column(name = "color")
    var color: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    var productType: ProductType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User?,

    @JsonIgnore
    @Column(name = "in_order")
    var inOrder: Boolean = false
)
