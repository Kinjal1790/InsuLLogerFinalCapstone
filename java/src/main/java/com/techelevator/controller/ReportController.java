package com.techelevator.controller;

import com.techelevator.dao.ReportDAO;
import com.techelevator.model.ReportDTO;
import com.techelevator.model.ReportFilterDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class ReportController {


    private ReportDAO reportDao;
    public ReportController(ReportDAO reportDao) {
        this.reportDao = reportDao;

    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/report", method = RequestMethod.POST)
    public List<ReportDTO> getReport(@Valid @RequestBody ReportFilterDataDTO reportFilterDataDto, @PathVariable int id) {
        return reportDao.filterReportData(reportFilterDataDto, id);

    }
}
