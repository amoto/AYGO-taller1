package co.edu.escuelaing.logservice.entities;

public class Log {
    private String text;
    private String date;

    public Log(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
