package com.techelevator.controller;

import com.techelevator.dao.ReadingLogDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.ReadingLogDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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




}