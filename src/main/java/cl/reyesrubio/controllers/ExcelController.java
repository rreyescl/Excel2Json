package cl.reyesrubio.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.reyesrubio.services.impl.ExcelReaderServiceImpl;

@RestController
@RequestMapping(ExcelController.EXCEL_RESOURCE)
public class ExcelController {

	public static final String EXCEL_RESOURCE = "/api/excel";
	@Autowired
	ExcelReaderServiceImpl excelReaderService;

	@RequestMapping(value="/import",method = RequestMethod.POST)
	public List<HashMap<String, String>> importar(@RequestParam("file") MultipartFile excelFile)
	{
		return excelReaderService.retornarObjeto(excelFile);
	}
}
