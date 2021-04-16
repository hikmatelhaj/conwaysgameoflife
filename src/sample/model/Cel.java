package sample.model;

public class Cel {
    private int status;

    public Cel(int g) {
        status = g;
    }

    public boolean getStatus() {
        if (status == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setStatus(boolean waarde) {
        if (waarde) {
           status = 1;
        }
        else {
            status = 0;
        }
    }
}
