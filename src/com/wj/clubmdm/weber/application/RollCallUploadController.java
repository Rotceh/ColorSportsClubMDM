/* 
 * Colors Sports Club 點名資料上傳 Controller
 * @author 黃郁授,吳彥儒
 * @date 2020/09/22
 */

package com.wj.clubmdm.weber.application;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.wj.clubmdm.weber.util.ChkDate;
import com.wj.clubmdm.weber.vo.RollCallDetail;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RollCallUploadController extends Application {
	
	private Logger logger = Logger.getLogger(RollCallUploadController.class);

	@FXML
	private DatePicker dpChoiceRollCallDate; //選擇點名日期
	@FXML
	private TextField tfRollCallDate; //點名日期
	@FXML
	private TextField tfFilePath; //點名檔絕對路徑
	@FXML
	private Button btnChoiceRollCallFile; //選擇點名檔
	@FXML
	private Button btnCheckData; //檢查檔案資料
	@FXML
	private TableView<RollCallDetail> tvRollCallDetail; //點名資料
	@FXML
	private TableColumn<RollCallDetail, String> colStudentNo; //點名資料_學員編號
	@FXML
	private TableColumn<RollCallDetail, String> colName; //點名資料_姓名
	@FXML
	private TableColumn<RollCallDetail, String> colDepartment; //點名資料_上課分部
	@FXML
	private TableColumn<RollCallDetail, String> colCourseKind; //點名資料_類別
	@FXML
	private TableColumn<RollCallDetail, String> colLevel; //點名資料_程度
	@FXML
	private TableColumn<RollCallDetail, String> colRollCallDate; //點名資料_點名日期	
	@FXML
	private TableColumn<RollCallDetail, String> colRollSpecial; //點名資料_特色課程	

	
	/*
	 * 初始化
	 */
	public void initialize() {
		//設定日期選擇器的格式
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			@Override public String toString(LocalDate date) {
				if(date !=null ) {
					return formatter.format(date);
				} else {
					return "";
				}
			}
			
			@Override public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, formatter);
				} else {
					return null;
				}
			}
		};
		dpChoiceRollCallDate.setConverter(converter);		
	}

	/*
	 * 將Excel檔案吃入
	 */
	public void checkRollCallFile() {
		chkField();
		
	}
	
	/*
	 * 檢查輸入欄位
	 */
	private boolean chkField() {
		//提示錯誤的對話框
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("資料輸入有誤");
				
		//檢核日期是否正確
		if (dpChoiceRollCallDate.getValue() == null) {			
			alert.setContentText("點名日期不正確！");
			alert.showAndWait();
			return false;			
		}
		return true;
	}
	
	/*
	 * 選擇點名檔(.xlsx)
	 */
	public void choiceFile() {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("選擇 點名檔(*.xlsx)...");
		filechooser.setInitialDirectory(new File("."));
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("EXCEL", "*.xlsx");
		filechooser.getExtensionFilters().add(filter);
		/*
		 * 跳出對話框時，有兩種方法取得獨佔視窗的對話框
		 * (1)透過button去取得Stage，這樣的效果會是獨佔模式
		 * (2)上一層在建立controller時，把stage當成參數傳入給controller的屬性，這樣可以直接透過屬性去取得stage
		 */
		File file = filechooser.showOpenDialog((Stage) btnChoiceRollCallFile.getScene().getWindow());
		if (file != null) {
			tfFilePath.setText(file.getAbsolutePath());			
		} else {
			tfFilePath.setText("");						
		}
	}
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		
	}
	
}
