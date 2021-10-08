package cl.reyesrubio.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IExcelReaderService {
	List<HashMap<String,String>> retornarObjeto(MultipartFile excelDataFile);
}
