package mobile.avengers.backend.models

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class CreateOrderRequest(
    var productIds: List<Long> = listOf(),
)
