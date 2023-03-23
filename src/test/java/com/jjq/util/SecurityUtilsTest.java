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

    public String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmYfyZkzDTWWc1nee2QxBXVyaNaChyo+DrPXGkzS2LqrSqzg7VsEGGlMuz3huoxsRoCYyLOITHVDdu34X2g1qr/2cwfRUhcKNa3yIxci0GRk4KfmTYOV1jFgQ5JXKiNmQhOlHB8K1O/ipci2SX4Iy7rirN6KrBT5oaL0/q87ue28RuyeqvRjiRPzhaWtiPFgCFmr04IfmMfQzEfrl1+98IH9xKTcPXUZdoYWGRCix/QRLXP1bfj/8r+D9sGUHjq9T2veF+7cX9y2VndVEehnc+eQwb/Rlmd+/CU7sTt9PSOjjYrsQr8tvWHc7KvW0WTtGjv5+g1FVyqLWtRWFxkwIEQIDAQAB";

    public String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZh/JmTMNNZZzWd57ZDEFdXJo1oKHKj4Os9caTNLYuqtKrODtWwQYaUy7PeG6jGxGgJjIs4hMdUN27fhfaDWqv/ZzB9FSFwo1rfIjFyLQZGTgp+ZNg5XWMWBDklcqI2ZCE6UcHwrU7+KlyLZJfgjLuuKs3oqsFPmhovT+rzu57bxG7J6q9GOJE/OFpa2I8WAIWavTgh+Yx9DMR+uXX73wgf3EpNw9dRl2hhYZEKLH9BEtc/Vt+P/yv4P2wZQeOr1Pa94X7txf3LZWd1UR6Gdz55DBv9GWZ378JTuxO309I6ONiuxCvy29Ydzsq9bRZO0aO/n6DUVXKota1FYXGTAgRAgMBAAECggEAXi/NYD7lbXzR26xk0ci37NubaxzRRKAIaroYiUREI5r+RLXpiU4/PTUcuYCrdvprNLFaBLjoewqaDKTNYyLp+W9/lGkc1TZGZdNvYM7A1cjn8LBAXqhGFcQNEP5n0s6N30vxHGyxnEnvQbF9Y4RPVK4Ls8TiYAQqL18ySWdYXHYSyVOP6qYI7E+tlRgZUXgjvXZeSkAOG6rgLqwN3CTmLgrJVx4NmpPEJ8MpxKvkjLA/Bs+UvpFGglLP8asYiHrM1kS1Cjad3OKIqwO6oXJBdBR20fqzbDMIGRjODzuPI+oP8GswYh5IfcgopJQrLqAkXn6bE+7QG+qHAeJfDtQGAQKBgQDIxCVZNzh4kisRiHk1gWdWfAFeQ9EGHa6tmCTZII3hGJWmtUc/7d8M3uX+xVVweMqrX3QSXgIcO8kryXWxGOwnFeTUPR6eVLKxLsvYjrG2B2Vlq/zoVvE07lNKK49w1J+cADfs4W+oHrWGUti0GGw5aN1EVYLQrgX15XyyOt5eIQKBgQDDxQ4KCqLzkAMAKFOCkktXHfsNarwU329tDbd7oNRgFQsAJkV/azfFpIQnEAxElpcCta051KEMS50X9Yw6dyRaBBF7iINslBFzmbo7nZeLiwuRnZS49mfn7YBfc8dhz10geiWpbF+LOBjVs5Qap1CiOVwB1KbLL7X6k7ji66oL8QKBgQC0nkI7kUJstpOoom7phBHD7KwOclSKfAm6gzYjYPZDnCqGYHBJQ2d33NBt9Hp5RTKQGsACBjE/1UmW6xfGy/Uo9Ll5d8MkDm70mHUrzrRyXxUJ80EEhbNbO04TXD0f9Sni3w9xPR+G0JnezonG/HHg9EacQ5H8y5LxNhSJBA0qAQKBgQClop77Pf6a5nizdGsbqV1XKwWTV4AkhNsIwLdCQVQOqx8wlDDu2q9IaKZrSnemvGauIzqaGF3eQkd60wU/HjjCdHi6NUlE345YaXOZJv/mT9GM9W+0gr5tC5Q2roW+Kq1XYiFlVE0ORHs2rAad+nwNlUxUPDCW7xogFUYzsEKicQKBgByZIWNQN5t8+KQ03pdwe9L/NaMUF6INCteyW61TzurvBMLcNtDcLLpAFL0eV5w0vgk4JZr/0NQqU6SRmZefABZYnMvfM7z8hlsSvGuJN4wV29zRjXnjnnbZLjGAbEorpco+uQ/YNSs3afUHOd2HU0e9qoeL1wn5pP1pAlwA+g6a";

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
