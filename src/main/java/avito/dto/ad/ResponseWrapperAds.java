package avito.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gturumtaev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapperAds {
    private Integer count;
    private List<AdDto> results;
}
