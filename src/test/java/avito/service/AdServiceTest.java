package avito.service;

import avito.dto.Role;
import avito.dto.ad.AdDto;
import avito.dto.ad.CreateAdDto;
import avito.entity.Ad;
import avito.entity.User;
import avito.mapper.AdMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import avito.repository.AdRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author gturumtaev
 */
@ExtendWith(MockitoExtension.class)
class AdServiceTest {
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;

    @Spy
    private AdMapper adMapper;
    @InjectMocks
    private AdService adService;

    @Test
    public void testGetAllAd() {
        List<Ad> adList = List.of(new Ad());

        when(adRepository.findAll()).thenReturn(adList);

        List<Ad> exp = adService.findAll();

        assertEquals(adList.size(), exp.size());
    }

    @Test
    public void testCreateAd() {
            Ad testAd = new Ad(10, null, "desc", "im", 123, "t", null);
            User author = new User(10, "email", "pass", "fn", "ln", "p",
                    "im", Role.USER, List.of(testAd), null);
            testAd.setAuthor(author);
            CreateAdDto testCAD = new CreateAdDto("t", 123, "t");

            Ad editedAd = new Ad(10, author, "desc", "im", 123, "t", null);
            AdDto expected = adMapper.mapEntityToDto(editedAd);

            when(adRepository.save(any())).thenReturn(any());

            AdDto result = adService.updateAdInfo(testAd, testCAD);

            assertEquals(expected, result);
        }
}