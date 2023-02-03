package org.example;

public class Check {
    private String check;

    private Long pid;

    public Check(String check, Long pid) {
        this.check = check;
        this.pid = pid;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
