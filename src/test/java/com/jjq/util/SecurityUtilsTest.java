package com.jjq.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/3/22
 * @desc
 */
public class SecurityUtilsTest {

    public String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqtklJs9dJhfAf+Bsp03mrQh+Lha0axPv\n" +
            "OU8zkR3xKtJGIUC+JNubvOzPeDcnmrSx+cZV13OtNXacVgaJgmbP6gQzrnBi38p/DD9yJQKzb8aF\n" +
            "5NupKqJroX7KWhR0VJcvTk0NvAQF5H8ZhcOLNDDRNuvQc+1UlGa6Vy9TWdFTgT/+4GptivX94iON\n" +
            "prY2VncrYIhe2lQe0wMPd8GcdnZEec9gdA8QcRT8TIlBT0Y2FEL/laxFIDixuLYq+mgY2lySxrw7\n" +
            "6dGPDP5VtFzKH9CA9gzkZnsg89IE+jVXDKTbrk8QPixcmlc5grKRJk8bUGM7ysysFI+eaRiIFvJe\n" +
            "O+94zwIDAQAB";

    public String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCq2SUmz10mF8B/4GynTeatCH4u\n" +
            "FrRrE+85TzORHfEq0kYhQL4k25u87M94NyeatLH5xlXXc601dpxWBomCZs/qBDOucGLfyn8MP3Il\n" +
            "ArNvxoXk26kqomuhfspaFHRUly9OTQ28BAXkfxmFw4s0MNE269Bz7VSUZrpXL1NZ0VOBP/7gam2K\n" +
            "9f3iI42mtjZWdytgiF7aVB7TAw93wZx2dkR5z2B0DxBxFPxMiUFPRjYUQv+VrEUgOLG4tir6aBja\n" +
            "XJLGvDvp0Y8M/lW0XMof0ID2DORmeyDz0gT6NVcMpNuuTxA+LFyaVzmCspEmTxtQYzvKzKwUj55p\n" +
            "GIgW8l4773jPAgMBAAECggEAFKY0IFG1z3HVeV81oq7+r3E0Vvu1iK+bHVEeSW7WBDfv8/rwQGIu\n" +
            "m5H/rWb1KXSnginfnC/GAo04bglSO2mTvZQObwn0WVoPKvrMH/8Ikvzql/OYqaeB3L4HVQXd3Y98\n" +
            "Tzlu7IeL8scN3aX5wDAaSUoNIsXPkDQuV3VH3LY5IojX00lK5E1f3bpXUZTUkPFgXszfjjJiH2+/\n" +
            "H/GMw9+QxeGXhFhPhz3/QIQ7yERBGWgsfKEjl0yJ47ZHevsz9Z7+6j2GkeLRnkkUm+lSIW8Tk45K\n" +
            "xYHVcid4qgXSWBCpMB1/GK2ul/Bp3iauFvwTs3f63CmMp9SJAy9dxRUGueIfuQKBgQDkmgCJ54v/\n" +
            "NTIFPJTqVp1WuqC2ZM4PnPq8fDe/xRsbnzSFh/ru4VAN8sAkSY3rDnz9TZcm7OYOrQkBdSCVrokF\n" +
            "VFYJ2MvUjgOoI6NKbaqRA4Yhmov23YXYeJ85TcEA2fRTZbQRbN9lYcT0/byI544mxZmwykRAdvcA\n" +
            "bE2oxToxrQKBgQC/UyTAtuTVoH5xX58cTb3t2e5N5gdTYMM9b4rflnz6EtxabFviOwLvf2Ovq6Uu\n" +
            "PLULd3EwJ+CsaPnyPxemTswRyUJDR3/5vcCzalTrlcwvyiG4fki45+PSOQvoW6uL8hknwG59/t0X\n" +
            "PLy8U65P2MON9DfmZldcypeobHfKmnU76wKBgQDXDb2V/jrogk2lOYHKFmN71KQeEiT78Sy8Jot3\n" +
            "n+DJknMhb3P0SAg8pvJ08LFOwNl6ngSlPNWMVuIS+fIGfiQu3FD4gHf4ZQUxpx0VhqegNlV3xWEI\n" +
            "gwtIQulfSjNYLrI3xoSoo+hhAqH767vyewtylA/qjc25a3Egd8JzE9+UHQKBgQCzp/tHC3buMvsI\n" +
            "suXRyh6jDpV7iJkwbhhnxCn/smxA1x0bu8y8LvWet1yARQmi1VacIdijg46x1xF9rEkk3bE6smYK\n" +
            "j0VY2hntX9fGMK+/4Gqxt8PJ/dYsPW01D3+Stg9xCH24ym4Jt/ryTRHN4Jog3kbvjwJSG9s1SEVk\n" +
            "do3HaQKBgBL6UJ2BlDAkQluM/aSHswMGZEobw75u9oS0v0mwbwgQcWu1tapMSJ9IbCPFKMGs5Qk4\n" +
            "moRdCz/gF66DqCIowNyimikZ0RlFbCA/ypc2z1NaJXb1P8fz35sF6BilpnMhv/ChBTCdyl9o1hXZ\n" +
            "2r24rJX/Oh+CGDWgeZNd7TYIGSuw";

    @Test
    public void generateKeyPairTest() throws Exception {
        SecurityUtils.generateKeyPair("RSA");
    }

    @Test
    public void rsaTest() {
        String secretContent = SecurityUtils.encryptInRSA("qingshan is not hill", publicKey);
        String content = SecurityUtils.decryptInRSA(secretContent, privateKey);
        Assertions.assertEquals("qingshan is not hill", content);
    }
}
