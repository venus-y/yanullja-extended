package com.battlecruisers.yanullja.place.dto;

import com.battlecruisers.yanullja.place.domain.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceQueryDto {

    @Schema(name = "숙소 id")
    private Long id;

    @Schema(name = "숙소 이름", example = "신라 호텔")
    private String name;

    @Schema(name = "숙소 대표 사진", example = "https://aws1.s3.ap-northeast-2.amazonaws.com/place/place1.jpg", description = "숙소 대표 사진의 url 받음")
    private String thumbnailImageUrl;

    private Integer minimumPrice;

    private Integer maximumPrice;

    private String region;

    @Schema(name = "카테고리")
    private String category;

    public static PlaceQueryDto from(Place place, LocalDate checkInDate,
        LocalDate checkOutDate) {

        return new PlaceQueryDto(
            place.getId(),
            place.getName(),
            place.getThumbnailImageUrl(),
            place.getMinimumPrice(checkInDate, checkOutDate),
            place.getMaxPrice(checkInDate, checkOutDate),
            place.getSubRegion().getName(),
            place.getCategory().getName()
        );
    }

}
