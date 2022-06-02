package com.techelevator.dao;

import com.techelevator.model.ReadingLogDTO;

import java.util.List;

public interface ReadingLogDAO {

    void insertingReadingLogData(ReadingLogDTO readingLogDto);
    List<ReadingLogDTO> getAllReadingLogs(int id);
    Double calculateBolus(ReadingLogDTO readingLogDto);

}
