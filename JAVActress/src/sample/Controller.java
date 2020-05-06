package sample;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {
    public TextField searchField;
    public Button searchBtn;
    public TableView<Actress> table;
    public TableColumn<Actress, Integer> orderedColumn;
    public TableColumn<Actress, String> idColumn;
    public TableColumn<Actress, String> nameColumn;
    public TableColumn<Actress, String> jpColumn;
    public ImageView imageChar;
    public Label nameLabel;
    public Label ageLabel;
    public static ObservableList<Actress> listActresses = FXCollections.observableArrayList();
    public static ObservableList<Video> listVideos = FXCollections.observableArrayList();
    public Label bwhLabel;
    public Label heightLabel;
    public Label birthdayLabel;
    public String url = "https://jav-rest-api-htpvmrzjet.now.sh/api/actress?name=";
    public String videoUrl = "https://jav-rest-api-htpvmrzjet.now.sh/api/videos/";
    public static ProgressIndicator progIndicator;
    public TableView<Video> tableCode;
    public TableColumn<Video, String> yearColumn;
    public TableColumn<Video, String> codeColumn;
    public TableColumn<Video, String> titleColumn;

    public void showAlert(String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");

        // Header Text: null
        alert.setHeaderText(headerText);
        alert.setContentText("Can't not be empty");

        alert.showAndWait();
    }

    public void lookUpActress(ActionEvent ae) {
        if (searchField.getText().isEmpty()) showAlert("Warning");
        else {
            HttpClient client = HttpClient.newHttpClient();
            String actressRequest = url + searchField.getText();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(actressRequest)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(Controller::parseInformation)
                    .join();
        }
        setCellTable();
    }

    public static String parseInformation(String responseBody) {
        JSONObject obj = new  JSONObject(responseBody);
        JSONArray list = obj.getJSONArray("result");
        for (int i = 0; i < list.length(); i++) {
            JSONObject actress = list.getJSONObject(i);
            String id = actress.getString("id");
            String name = actress.getString("name");
            String jpName = actress.getString("japanName");
            String bust, waist, hip, height, birthday;
            if (actress.isNull("bust")
                    || actress.isNull("waist")
                    || actress.isNull("hip")
                    || actress.isNull("height")
                    || actress.isNull("birthday")) {
                bust = "?";
                waist = "?";
                hip = "?";
                height = "?";
                birthday = "?";
            }
            else {
                bust = actress.getString("bust");
                waist = actress.getString("waist");
                hip = actress.getString("hip");
                height = actress.getString("height");
                birthday = actress.getString("birthday");
            }
            String imageUrl = actress.getString("imageUrl");
            String siteUrl = actress.getString("siteUrl");
            System.out.println(id +"  " +name + "  " + jpName +  "  "+  bust + waist + hip);
            listActresses.add(new Actress(id, name, jpName,bust, waist, hip, height, birthday, imageUrl,siteUrl));

        }

        return null;
    }

    public void setCellTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        jpColumn.setCellValueFactory(new PropertyValueFactory<>("japaneseName"));
        table.setItems(listActresses);
    }

    public void setCellValueCode() {
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableCode.setItems(listVideos);
    }

    @FXML
    private void tableClicked(MouseEvent me) {
        Actress actress = table.getSelectionModel().getSelectedItem();
        if (actress == null) {
            System.out.println("null");
        }
        else {
            nameLabel.setText(actress.getName());
            ageLabel.setText(actress.getJapaneseName());
            imageChar.setImage(new Image(actress.getImageUrl()));
            bwhLabel.setText("BWH: " + actress.getBust() + "-" + actress.getWaist() + "-" + actress.getHip());
            heightLabel.setText(actress.getHeight() + " cm");
            birthdayLabel.setText(actress.getBirthday());

            lookUpCode(actress);
        }
    }

    public void lookUpCode(Actress selected){
        HttpClient client = HttpClient.newHttpClient();
        String actressRequest = videoUrl + selected.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(actressRequest)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Controller::parseCode)
                .join();
        setCellValueCode();
    }

    public static String parseCode(String responseBody) {
        JSONObject obj = new  JSONObject(responseBody);
        JSONArray list = obj.getJSONArray("result");
        for (int i = 0; i < list.length(); i++) {
            JSONObject videos = list.getJSONObject(i);
            String title = videos.getString("name");
            String code = videos.getString("siteUrl").split("/")[8].split("=")[1];
            String year = videos.getString("date").split("-")[0];
            listVideos.add(new Video(year, code, title));
        }
        return null;
    }

}
