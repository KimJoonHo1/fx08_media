package ex01;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller implements Initializable{
	Parent root;
	ListView<String> fxListView;
	ImageView fxImageView;
	ObservableList<String> phoneString;
	ObservableList<Phone> phoneURL;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	
	public void setRoot(Parent root) {
		this.root = root;
		fxListView = (ListView)root.lookup("#fxListView");
		fxImageView = (ImageView)root.lookup("#fxImageView");
		setListView();
	}
	
	public void setListView() {
		setList();
		fxListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)->{
			System.out.println("observable(형식) : " + observable);
			System.out.println("oldValue(형식) : " + oldValue);
			System.out.println("newValue(형식) : " + newValue);
			System.out.println(phoneString.get((int)newValue));
			System.out.println(phoneURL.get((int)newValue).getSmartPhone());
			System.out.println(phoneURL.get((int)newValue).getImage());
			fxImageView.setImage(new Image("/img/phone/" + phoneURL.get((int)newValue).getImage()));
		});
	}
	
	public void setList() {
		phoneString = FXCollections.observableArrayList();
		phoneURL = FXCollections.observableArrayList();
		for(int i=1; i<8; i++) {
			phoneString.add("갤럭시S"+i);
			Phone phone = new Phone("갤럭시S"+i, "phone0"+i+".png");
			phoneURL.add(phone);
		}
		fxListView.setItems(phoneString);
	}
	
	public void btn() {
		System.out.println("버튼 클릭");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AAA.fxml"));
		Parent newRoot = null;
		Scene scene = null;
		try {
			newRoot = loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
		scene = new Scene(newRoot);
		Stage stage = (Stage)root.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
