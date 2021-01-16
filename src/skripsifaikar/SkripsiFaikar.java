
package skripsifaikar;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SkripsiFaikar {

    private String inputFile;
    private void setInputFile(String inputFile){
        this.inputFile = inputFile;
    }
     public void read() throws IOException, BiffException {
        File inputWorkbook = new File(inputFile);
        Workbook wb;
        try {
            wb = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = wb.getSheet(0);
            int x = sheet.getColumns();
            int y = sheet.getRows();
            String[][]array = new String [x][y];
            for(int j = 0; j < sheet.getRows(); j++) {
                for(int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i,j);
                    CellType type = cell.getType();
                    String content = cell.getContents();
                    array[i][j] = content;
                }
            }
            for(int i = 0; i < y; i++) {
                for(int j = 0; j < x; j++) {
                    System.out.println(String.format("%-10s", array[j][i]+" "));
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (BiffException be) {
            be.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException, BiffException {
        SkripsiFaikar test = new SkripsiFaikar();
        test.setInputFile("datareview.xlsx");
        test.read();
        System.out.println("****************************************************************************************************************************************************************************************************");
        System.out.println("TOKENIZING");
    }
}
