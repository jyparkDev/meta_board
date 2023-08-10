package com.meta.board.domain.excel;

import com.meta.board.domain.board.BoardDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcelUtils {



    public Workbook makeExcelFile(Workbook wb,List<BoardDto> boardList){
        String sheetName = "게시글 데이터";
        Sheet sheet = wb.createSheet("sheetName");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        rowNum = makeExcelHeader(sheet,rowNum,row,cell);
        makeExcelBody(boardList, sheet, rowNum, row,cell);

        return wb;
    }
    private int makeExcelHeader(Sheet sheet,int rowNum,Row row, Cell cell) {
        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("제목");
        cell = row.createCell(2);
        cell.setCellValue("댓글수");
        cell = row.createCell(3);
        cell.setCellValue("작성자");
        cell = row.createCell(4);
        cell.setCellValue("생성일");
        cell = row.createCell(5);
        cell.setCellValue("조회수");

        return rowNum;
    }

    private void makeExcelBody(List<BoardDto> boardList, Sheet sheet, int rowNum
            , Row row, Cell cell) {

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
            cell = row.createCell(5);
            cell.setCellValue(boardDto.getViewNum());
        }
    }


}
