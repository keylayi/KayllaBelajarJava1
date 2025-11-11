package model;

import java.time.LocalDate;

public class DataKesehatan {
    private String nama;
    private LocalDate tanggal;
    private double suhu;
    private String tekananDarah;
    private String aktivitas;

    public DataKesehatan(String nama, LocalDate tanggal, double suhu, String tekananDarah, String aktivitas) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.suhu = suhu;
        this.tekananDarah = tekananDarah;
        this.aktivitas = aktivitas;
    }

    // Getter methods
    public String getNama() {
        return nama;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public double getSuhu() {
        return suhu;
    }

    public String getTekananDarah() {
        return tekananDarah;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    // Setter methods
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setSuhu(double suhu) {
        this.suhu = suhu;
    }

    public void setTekananDarah(String tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }
}