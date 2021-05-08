package com.junction.stupidhack.alarm.repository;

import com.junction.stupidhack.alarm.model.Alarm;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AlarmRepository extends CrudRepository<Alarm, Long> {
    List<Alarm> getByCreatorLoginOrderByDateDesc(String creatorLogin);
    List<Alarm> getByDestinationLoginAndDateAfterAndDateBeforeOrderByDateDesc(String destinationLogin,
                                                                              Date after, Date before);

}
