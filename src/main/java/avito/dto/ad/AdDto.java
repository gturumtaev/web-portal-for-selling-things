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
public class AdDto {
    private Integer pk;
    private String image;
    private Integer price;
    private String title;
    private Integer author;
}
