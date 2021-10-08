package cl.reyesrubio.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cl.reyesrubio.services.IExcelReaderService;

@Service
public class ExcelReaderServiceImpl implements IExcelReaderService{

	@Override
	public List<HashMap<String, String>> retornarObjeto(MultipartFile excelDataFile) {
		List<HashMap<String, String>> lista = new ArrayList<HashMap<String,String>>();
		try {
			//traer la hoja de trabajo
			Workbook workbook = new XSSFWorkbook(excelDataFile.getInputStream());
			HashMap<String, String> objeto = null;
			//la hoja segun el indice indicado
			Sheet sheet = workbook.getSheetAt(0);
			//obtengo las filas
			int rows = sheet.getLastRowNum()+1;
			//obtengo la ultima columna de la fila 0
			int columns = sheet.getRow(0).getPhysicalNumberOfCells();
			//obtengo los campos por separado
			List<String> campos = new ArrayList<String>();
			for(int i = 0; i< columns;i++) {
				campos.add(sheet.getRow(0).getCell(i).toString());
			}
			//recorro filas
			for(int fila = 1; fila < rows;fila++) {
				//se crea un objeto tomando el campo y su valor
				objeto = new HashMap<String, String>();
				int col = 0;
				
				for(String campo: campos) {
					objeto.put(campo, sheet.getRow(fila).getCell(col).toString());
					col++;
				}				 
				lista.add(objeto);				
			}
			System.out.println("filas ->" + rows + " columnas " + columns);
			return lista;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}
