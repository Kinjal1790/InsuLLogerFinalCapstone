package com.techelevator.dao;

import com.techelevator.model.ActivityDTO;
import com.techelevator.model.AlertInfoDTO;
import com.techelevator.model.ReadingLogDTO;

import java.util.List;

public interface ReadingLogDAO {

    Integer insertingReadingLogData(ReadingLogDTO readingLogDto);
    List<ActivityDTO> getAllActivityLogs(int id);

    List<ActivityDTO> getAllUserActivityLog();

//    List<ReadingLogDTO> getAllReadingLogs(int id);
    AlertInfoDTO calculateBolus(ReadingLogDTO readingLogDto, int readingLogId);

}
