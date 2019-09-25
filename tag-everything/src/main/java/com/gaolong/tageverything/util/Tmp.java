package com.gaolong.tageverything.util;

public class Tmp {
    public static void main(String[] args) {
        String url = "http://video.chuangkr.china.com.cn/201707/08032726/zsm3k2an8md6clyk_fmp4_vb64.mp4";
        String urlPath = getUrlPath(url);
        System.out.println(urlPath);
    }

    private static String getUrlPath(String url) {
        String domain = "http://video.chuangkr.china.com.cnabc";
        int index = url.indexOf(domain);
        if (index  == -1) {
            throw new IllegalArgumentException("bad_url");
        }
        return url.substring(domain.length());
    }
}
