package com.techelevator.controller;

import com.techelevator.dao.ReadingLogDAO;
import com.techelevator.model.ActivityDTO;
import com.techelevator.model.AlertInfoDTO;
import com.techelevator.model.ReadingLogDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public AlertInfoDTO getBolus(@Valid @RequestBody ReadingLogDTO readingLogDto) {
        Integer readingLogId = readingLogDao.insertingReadingLogData(readingLogDto);
        return readingLogDao.calculateBolus(readingLogDto, readingLogId);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/{id}/activity", method = RequestMethod.GET)
    public List<ActivityDTO> getAllActivity(@PathVariable int id) {
        return readingLogDao.getAllActivityLogs(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/activity", method = RequestMethod.GET)
    public List<ActivityDTO> getAllUserActivity() {
        return readingLogDao.getAllUserActivityLog();
    }



}