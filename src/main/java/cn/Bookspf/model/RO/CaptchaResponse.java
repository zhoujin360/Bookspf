package cn.Bookspf.model.RO;

import java.awt.image.BufferedImage;

public class CaptchaResponse {
    private String captcha;
    private String imgJSON;

    public CaptchaResponse() {}

    public CaptchaResponse(String captcha, String imgJSON) {
        this.captcha = captcha;
        this.imgJSON = imgJSON;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getImgJSON() {
        return imgJSON;
    }

    public void setImgJSON(String imgJSON) {
        this.imgJSON = imgJSON;
    }
}
