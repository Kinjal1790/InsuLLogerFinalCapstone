package com.techelevator.dao;

import com.techelevator.model.ReportDTO;
import com.techelevator.model.ReportFilterDataDTO;

import java.util.List;

public interface ReportDAO {


    List<ReportDTO> filterReportData(ReportFilterDataDTO reportDto, int id);
}
