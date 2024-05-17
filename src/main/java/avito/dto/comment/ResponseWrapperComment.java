package avito.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gturumtaev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperComment {
    private Integer count;
    private List<FullCommentDto> results;
}
