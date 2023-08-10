package com.meta.board.controller;

import com.meta.board.domain.Condition;
import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.domain.excel.ExcelUtils;
import com.meta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private final BoardMapper mapper;
    private final ExcelUtils excelUtils;

    @GetMapping("/excel/download")
    public void excelDownload(Condition condition, HttpServletResponse response) throws IOException {

        log.info("con : {}" , condition);
        if (condition.getSort().equals("old_date")){
            condition.setSort("create_date");
            condition.setDir("DESC");
        }
        log.info("con : {}",condition);
        List<BoardDto> boardList = mapper.findAllForExcel(condition);

//        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();

        wb = excelUtils.makeExcelFile(wb,boardList);

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }


}
