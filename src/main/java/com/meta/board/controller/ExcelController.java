package com.meta.board.controller;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExcelController {

    private final BoardMapper mapper;

    @GetMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) throws IOException {

        List<BoardDto> boardList = mapper.findAllForExcel();

//        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("제목");
        cell = row.createCell(2);
        cell.setCellValue("작성자");
        cell = row.createCell(3);
        cell.setCellValue("내용");
        cell = row.createCell(4);
        cell.setCellValue("작성일자");
        cell = row.createCell(5);
        cell.setCellValue("조회수");

        // Body
        for (BoardDto boardDto : boardList) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(boardDto.getId());
            cell = row.createCell(1);
            cell.setCellValue(boardDto.getTitle());
            cell = row.createCell(2);
            cell.setCellValue(boardDto.getWriter());
            cell = row.createCell(3);
            cell.setCellValue(boardDto.getContent());
            cell = row.createCell(4);
            cell.setCellValue(boardDto.getCreateDate());
            System.out.println(boardDto.getCreateDate());
            System.out.println(boardDto.getViewNum());
            cell = row.createCell(5);
            cell.setCellValue(boardDto.getViewNum());
        }


        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
