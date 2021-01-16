
package skripsifaikar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Baca_Excel {
    private String inputFile;
    private ArrayList<String> dataFix;
    private ArrayList<String> labelDataFix;
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayList<String> getDataFix() {
        return dataFix;
    }

    public void setDataFix(ArrayList<String> dataFix) {
        this.dataFix = dataFix;
    }

    public ArrayList<String> getLabelDataFix() {
        return labelDataFix;
    }

    public void setLabelDataFix(ArrayList<String> labelDataFix) {
        this.labelDataFix = labelDataFix;
    }
    
    public void read() throws IOException, BiffException {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> dataLabel = new ArrayList<>();
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0); //Sheet 0 ini sama dengan sheet pertama pada excel
            int x = sheet.getColumns(); 
            int y = sheet.getRows();
            String[][] array = new String [x][y];
            for (int j = 0; j < sheet.getRows(); j++) {
                for(int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    CellType type = cell.getType();
                    String content = cell.getContents();
                    array[i][j] = content;
                }
            }

            for (int j = 3; j < x-1; j++) {
            for(int i = 1; i < y; i++) {
                data.add(array[j][i]);
            }
                setDataFix(data);
            }
            for (int j = 4; j < x; j++) {
            for(int i = 1; i < y; i++) {
                dataLabel.add(array[j][i]);
            }
                setLabelDataFix(dataLabel);}
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException, BiffException {
        Baca_Excel test = new Baca_Excel();
        test.setInputFile("datareview.xls");
    }
}
