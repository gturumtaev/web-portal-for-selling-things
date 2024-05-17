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
public class FullAdDto {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}
