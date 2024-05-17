package avito.service;

import avito.dto.ad.AdDto;
import avito.dto.ad.CreateAdDto;
import avito.dto.ad.FullAdDto;
import avito.entity.Ad;
import avito.entity.User;
import avito.mapper.AdMapper;
import avito.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author rvorozheikin
 */
@Service
@RequiredArgsConstructor
public class AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserService userService;
    private final ImageService imageService;

    /**
     * Return all ads
     * @return {@link List<  avito.entity.Ad  >} of {@link avito.entity.Ad}
     * @author gturumtaev
     */
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    /**
     * Create new ad
     * @param email author's username (email)
     * @param file new ad's image
     * @param createAdDto new ad's data
     * @return created ad in {@link avito.dto.ad.AdDto} instance
     * @author gturumtaev
     */
    @Transactional
    public AdDto createAd(String email, MultipartFile file, CreateAdDto createAdDto) {
        Ad newAd = adMapper.mapCreateAdDtoToAd(createAdDto);

        newAd.setAuthor(userService.findUserByEmail(email));
        newAd = save(newAd);

        newAd.setImage(imageService.uploadImageAd(newAd.getPk(), file));
        save(newAd);
        return adMapper.mapAdToAdDto(newAd);
    }

    /**
     * Save ad
     * @param ad target {@link Ad}
     * @return saved {@link Ad}
     * @author gturumtaev
     */
    private Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    /**
     * Return target ad
     * @param adId id of target ad
     * @return {@link Optional<Ad>} with target {@link Ad}, <br>
     * {@link Optional#empty()} if target ad not existed
     * @author gturumtaev
     */
    public Optional<Ad> findById(Integer adId) {
        return adRepository.findById(adId);
    }

    /**
     * Create {@link avito.dto.ad.FullAdDto} instance
     * @param ad target {@link Ad}
     * @return {@link avito.dto.ad.FullAdDto} instance for target {@link Ad}
     * @author gturumtaev
     */
    public FullAdDto createFullAdDto(Ad ad) {
        User author = ad.getAuthor();
        return adMapper.mapAdAndAuthorToFullAdDto(author, ad);
    }
    /**
     * Delete target ad and it's image
     * @param adId id of target ad
     */
    public void deleteById(Integer adId) {
        imageService.deleteAdImage(adId);
        adRepository.deleteById(adId);
    }
    /**
     * Edit target ad
     * @param targetAd target {@link Ad}
     * @param newData {@link CreateAdDto} with new ad's data
     * @return edited ad in {@link AdDto} instance
     * @author gturumtaev
     */
    public AdDto updateAdInfo(Ad targetAd, CreateAdDto newData) {
        targetAd.setTitle(newData.getTitle());
        targetAd.setPrice(newData.getPrice());
        targetAd.setDescription(newData.getDescription());
        save(targetAd);
        return adMapper.mapAdToAdDto(targetAd);
    }
    /**
     * Edit ad's image
     * @param ad target {@link Ad}
     * @param image {@link MultipartFile} with new image
     * @return {@code byte[]} - bytes of new image
     * @author gturumtaev
     */
    @Transactional
    public byte[] updateAdImage(Ad ad, MultipartFile image) {
        imageService.uploadImageAd(ad.getPk(), image);
        return imageService.getImageAd(ad.getPk());
    }
}
