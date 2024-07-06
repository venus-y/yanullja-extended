package com.battlecruisers.yanullja.room;


import com.battlecruisers.yanullja.reservation.ReservationService;
import com.battlecruisers.yanullja.room.domain.Room;
import com.battlecruisers.yanullja.room.domain.RoomType;
import com.battlecruisers.yanullja.room.dto.RoomNameDto;
import com.battlecruisers.yanullja.room.dto.RoomQueryDto;
import com.battlecruisers.yanullja.room.dto.RoomReservationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final ReservationService reservationService;

    public RoomQueryDto getRoom(Long roomId, LocalDate checkInDate, LocalDate checkoutDate, RoomType roomType) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(IllegalArgumentException::new);

        /**
         * 나중에 숙박 대실 모두 고려한 Dto 완성시 사용할 로직
         * */


        return RoomQueryDto.from(room, checkInDate, checkoutDate, 10);
    }

    public List<RoomNameDto> getAllRoomsName(Long placeId) {
        return roomRepository.findAllRoomNameDtosByPlaceId(placeId);
    }

    @Transactional(readOnly = true)
    public RoomReservationInfoDto queryRoomDetailForReservation(Long roomId) {
        Room room = roomRepository.queryRoomById(roomId)
                .orElseThrow(IllegalArgumentException::new);

        return RoomReservationInfoDto.createRoomReservationInfoDto(room);
    }
}
