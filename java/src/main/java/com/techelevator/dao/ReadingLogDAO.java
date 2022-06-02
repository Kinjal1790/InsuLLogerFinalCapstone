package com.techelevator.dao;

import com.techelevator.model.ReadingLogDTO;
import com.techelevator.model.UserInfoDTO;

import java.util.List;

public interface ReadingLogDAO {

    Integer insertingReadingLogData(ReadingLogDTO readingLogDto);
    List<ReadingLogDTO> getAllReadingLogs(int id);
}
