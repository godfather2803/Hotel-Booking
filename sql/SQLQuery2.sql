SELECT tblRoom.roomID, tblRoom.roomTypeID, tblRoomType.roomType,
tblRoomType.price, tblRoomType.description
FROM tblRoom
INNER JOIN tblRoomType ON tblRoom.roomTypeID = tblRoomType.roomTypeID
WHERE tblRoom.status = 1
AND tblRoom.roomID NOT IN 
(
SELECT tblBookingDetail.roomID FROM tblBookingDetail
 WHERE tblBookingDetail.checkOutDate > '2020-12-15' /*search Check-in*/
 AND
 tblBookingDetail.checkInDate < '2020-12-20' /*search Check-out*/
)

SELECT tblBookingDetail.roomID FROM tblBookingDetail WHERE tblBookingDetail.checkInDate < '2020-12-20'