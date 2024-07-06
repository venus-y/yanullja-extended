package com.battlecruisers.yanullja.coupon.domain;

import com.battlecruisers.yanullja.base.BaseDate;
import com.battlecruisers.yanullja.room.domain.Room;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon extends BaseDate {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @Nullable
    Room room;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal minimumPrice;
    private BigDecimal discountPrice;
    private Double discountRate;
    private BigDecimal discountLimit;
    private String description;
    @Column
    private String region;
    @Column
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Boolean isValid;

    private Boolean isRegistered;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    protected Coupon(String name, BigDecimal minimumPrice,
        BigDecimal discountPrice,
        Double discountRate, BigDecimal discountLimit, String description,
        String region, RoomType roomType, Room room, boolean isValid,
        boolean isRegistered) {
        this.name = name;
        this.minimumPrice = minimumPrice;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.discountLimit = discountLimit;
        this.description = description;
        this.region = region;
        this.roomType = roomType;
        this.room = room;
        this.isValid = isValid;
        this.isRegistered = isRegistered;
    }

    public Coupon(Long couponId) {
        this.id = couponId;
    }

    public static Coupon createCoupon(String name, BigDecimal minimumPrice,
        BigDecimal discountPrice,
        Double discountRate, BigDecimal discountLimit, String description,
        String region, RoomType roomType, Room room, boolean isValid,
        boolean isRegistered) {
        return new Coupon(name, minimumPrice, discountPrice, discountRate,
            discountLimit, description, region, roomType, room, isValid,
            isRegistered);
    }


    @PrePersist
    public void setValidity() {
        LocalDate currentDate = LocalDate.now();

        this.validityStartDate = currentDate;

        this.validityEndDate = currentDate.plusWeeks(2);
    }

    public void changeRegistrationStatus() {
        this.isRegistered = true;
    }


}
