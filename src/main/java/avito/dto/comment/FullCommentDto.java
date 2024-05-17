package avito.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author gturumtaev
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FullCommentDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createAt;
    private Integer pk;
    private String text;
}
