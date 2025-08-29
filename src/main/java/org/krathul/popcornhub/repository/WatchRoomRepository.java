package org.krathul.popcornhub.repository;

import org.krathul.popcornhub.model.WatchRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WatchRoomRepository extends MongoRepository<WatchRoom, String> {

}
