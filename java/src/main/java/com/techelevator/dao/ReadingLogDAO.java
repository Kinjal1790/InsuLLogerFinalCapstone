package com.techelevator.dao;

import com.techelevator.model.Bolus;
import com.techelevator.model.ReadingLogDTO;
import com.techelevator.model.UserInfoDTO;

import java.util.List;

public interface ReadingLogDAO {

    int insertingReadingLogData(ReadingLogDTO readingLogDto);
    List<ReadingLogDTO> getAllReadingLogs(int id);
    Bolus calculateBolus(int id);

}
