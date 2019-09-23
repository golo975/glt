package com.gaolong.tageverything;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

public class GenerateFileTest {

    @Test
    public void Test() {
//        int i = 6001;
//        System.out.println(String.format("%02d:%02d", i / 60, i % 60));
//        long endTime = Instant.now().atZone(ZoneId.systemDefault()).plusYears(2).getNano() / 1_000_000L;
//        System.out.println(endTime);

        System.out.println(Instant.now().atZone(ZoneId.systemDefault()).plusYears(2).toInstant().toEpochMilli());

//        System.out.println(Instant.now());
//
//        System.out.println(Instant.ofEpochMilli(1630917338400L));
    }

    public static void main(String[] args) {
        List<ListOrderedMap<String, String>> list = new ArrayList<>();
//        {
//            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
//            replaceStringMap.put("KryptonArticleMapping", "KryptonNewsflashMapping");
//            replaceStringMap.put("KryptonSyncArticle", "KryptonSyncNewsflash");
//
//            replaceStringMap.put("kryptonArticleMapping", "kryptonNewsflashMapping");
//            replaceStringMap.put(".ARTICLE.", ".NEWSFLASH.");
//            replaceStringMap.put("WidgetArticle", "WidgetNewsflash");
//            list.add(replaceStringMap);
//        }

        /*{
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("KryptonSyncArticle", "KryptonSyncTheme");
            replaceStringMap.put("KryptonArticleMapping", "KryptonThemeMapping");

            replaceStringMap.put("kryptonArticleMapping", "kryptonThemeMapping");
            replaceStringMap.put(".ARTICLE.", ".THEME.");
            replaceStringMap.put("WidgetArticle", "WidgetTheme");
            replaceStringMap.put("krypton_sync_article", "krypton_sync_theme");
            list.add(replaceStringMap);
        }

        for (ListOrderedMap<String, String> replaceStringMap : list) {
            List<String> paths = new ArrayList<>();
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/task/sync/krypton/handler/KryptonSyncArticleJobHandler.java");
//            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/task/sync/krypton/mapping/KryptonArticleMapping.java");
            paths.forEach(path -> replace(path, replaceStringMap));
        }*/


        System.out.println("\u5f85\u53d1\u5e03090301");

    }

    private static void replace(String formatFilePath, ListOrderedMap<String, String> replaceStringMap) {
        try {
            File oldFile = new File(formatFilePath);
            File newFile = new File(formatFilePath.replace(replaceStringMap.get(0), replaceStringMap.getValue(0)));

            System.out.println(oldFile.getAbsolutePath());
            System.out.println(newFile.getAbsolutePath());

            List<String> lines = FileUtils.readLines(oldFile, Charset.defaultCharset());
            for (int i = 0; i < lines.size(); i++) {
                String line = StringUtils.replaceEach(lines.get(i),
                        replaceStringMap.keySet().toArray(new String[0]),
                        replaceStringMap.values().toArray(new String[0]));

                FileUtils.writeStringToFile(newFile, line, Charset.defaultCharset(), i != 0);
                FileUtils.write(newFile, "\n", Charset.defaultCharset(), true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
