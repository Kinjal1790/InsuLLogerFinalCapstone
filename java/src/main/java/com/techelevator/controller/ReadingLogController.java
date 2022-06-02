package com.techelevator.controller;

import com.techelevator.dao.ReadingLogDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.ReadingLogDTO;
import com.techelevator.model.UserInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class ReadingLogController {

    private ReadingLogDAO readingLogDao;

    public ReadingLogController(ReadingLogDAO readingLogDao) {
        this.readingLogDao = readingLogDao;
    }

    @ResponseStatus(HttpStatus.CREATED)

    @RequestMapping(value = "/bolus", method = RequestMethod.POST)
    public void readingLogInput(@Valid @RequestBody ReadingLogDTO readingLogDto) {
        Integer readingLogId = readingLogDao.insertingReadingLogData(readingLogDto);
//        calculatedBolus(readingLogId);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/reading_log/{id}", method = RequestMethod.GET)
    public List<ReadingLogDTO> getAllReadingLogs(@PathVariable int id) {
        return readingLogDao.getAllReadingLogs(id);
    }




}