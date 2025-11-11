package controller;

import model.DataKesehatan;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomepageController implements Initializable {

    @FXML
    private TextField tfNama;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private TextField tfSuhu;
    @FXML
    private TextField tfTekananDarah;
    @FXML
    private ComboBox<String> cbAktivitas;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnHapus;
    @FXML
    private TableView<DataKesehatan> tabelData;
    @FXML
    private TableColumn<DataKesehatan, String> coltfNama;
    @FXML
    private TableColumn<DataKesehatan, LocalDate> coldpTanggal;
    @FXML
    private TableColumn<DataKesehatan, Double> coltfSuhu;
    @FXML
    private TableColumn<DataKesehatan, String> coltfTekananDarah;
    @FXML
    private TableColumn<DataKesehatan, String> colcbAktivitas;

    private ObservableList<DataKesehatan> dataList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi ObservableList
        dataList = FXCollections.observableArrayList();

        // Isi pilihan ComboBox
        cbAktivitas.setItems(FXCollections.observableArrayList("Ringan", "Sedang", "Berat"));

        // Setup kolom tabel - SESUAIKAN DENGAN fx:id DI FXML
        coltfNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        coldpTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        coltfSuhu.setCellValueFactory(new PropertyValueFactory<>("suhu"));
        coltfTekananDarah.setCellValueFactory(new PropertyValueFactory<>("tekananDarah"));
        colcbAktivitas.setCellValueFactory(new PropertyValueFactory<>("aktivitas"));

        // Hubungkan tabel dengan list
        tabelData.setItems(dataList);

        // Listener untuk menampilkan detail saat baris dipilih
        tabelData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDataDetails(newValue));
    }

    private void showDataDetails(DataKesehatan data) {
        if (data != null) {
            tfNama.setText(data.getNama());
            dpTanggal.setValue(data.getTanggal());
            tfSuhu.setText(String.valueOf(data.getSuhu()));
            tfTekananDarah.setText(data.getTekananDarah());
            cbAktivitas.setValue(data.getAktivitas());
        } else {
            clearFields();
        }
    }

    @FXML
    private void handleTambah(ActionEvent event) {
        try {
            String nama = tfNama.getText();
            LocalDate tanggal = dpTanggal.getValue();
            double suhu = Double.parseDouble(tfSuhu.getText());
            String tekanan = tfTekananDarah.getText();
            String aktivitas = cbAktivitas.getValue();

            if (nama.isEmpty() || tanggal == null || tekanan.isEmpty() || aktivitas == null) {
                showAlert(Alert.AlertType.WARNING, "Input Error", "Semua field wajib diisi!");
                return;
            }

            DataKesehatan dataBaru = new DataKesehatan(nama, tanggal, suhu, tekanan, aktivitas);
            dataList.add(dataBaru);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Suhu harus berupa angka!");
        }
    }

    @FXML
    private void handleEdit(ActionEvent event) {
        DataKesehatan selected = tabelData.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setNama(tfNama.getText());
                selected.setTanggal(dpTanggal.getValue());
                selected.setSuhu(Double.parseDouble(tfSuhu.getText()));
                selected.setTekananDarah(tfTekananDarah.getText());
                selected.setAktivitas(cbAktivitas.getValue());

                tabelData.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil diperbarui!");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Suhu harus berupa angka!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih data yang ingin diubah!");
        }
    }

    @FXML
    private void handleHapus(ActionEvent event) {
        DataKesehatan selected = tabelData.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dataList.remove(selected);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih data yang ingin dihapus!");
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        tfNama.clear();
        dpTanggal.setValue(null);
        tfSuhu.clear();
        tfTekananDarah.clear();
        cbAktivitas.setValue(null);
        tabelData.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}