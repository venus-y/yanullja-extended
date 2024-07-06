package com.battlecruisers.yanullja.purchase.dto;

import com.battlecruisers.yanullja.member.domain.Member;
import com.battlecruisers.yanullja.room.domain.Room;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PurchaseDto {

    private Long id;
    private BigDecimal price;
    private Boolean paymentStatus;
    private Member member;
    private Room room;

}
