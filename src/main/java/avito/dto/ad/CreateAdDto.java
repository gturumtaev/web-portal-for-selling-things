package avito.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gturumtaev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdDto {
    private String title;
    private Integer price;
    private String description;
}
