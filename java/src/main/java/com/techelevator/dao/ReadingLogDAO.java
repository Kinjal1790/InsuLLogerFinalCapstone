package com.techelevator.dao;

import com.techelevator.model.ActivityDTO;
import com.techelevator.model.ReadingLogDTO;

import java.util.List;

public interface ReadingLogDAO {

    Integer insertingReadingLogData(ReadingLogDTO readingLogDto);
    List<ActivityDTO> getAllActivityLogs(int id);

//    List<ReadingLogDTO> getAllReadingLogs(int id);
    Double calculateBolus(ReadingLogDTO readingLogDto, int readingLogId);

}
