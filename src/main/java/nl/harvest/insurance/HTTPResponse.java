package nl.harvest.insurance;

public class HTTPResponse {

    private String connection;
    private String code;
    private String date;
    private String host;

    public HTTPResponse() {

        connection = null;
        code = null;
        date = null;

    }

    public String getConnection() {

        return this.connection;

    }

    public String getCode() {

        return this.code;

    }

    public String getDate() {

        return this.date;

    }

    public void setConnection(String code) {

        this.connection = connection;

    }

    public void setCode(String code) {

        this.code = code;

    }

    public void setData(String date) {

        this.date = date;

    }

}
