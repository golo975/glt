package com.gaolong.tageverything;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GenerateFileTest {


    public static void main(String[] args) {
        List<ListOrderedMap<String, String>> list = new ArrayList<>();
//        {
//            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
//            replaceStringMap.put("Mod", "AudioRelationList");
//            list.add(replaceStringMap);
//        }
//        {
//            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
//            replaceStringMap.put("List", "AudioInsertList");
//            list.add(replaceStringMap);
//        }
//        {
//            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
//            replaceStringMap.put("List", "VideoInsertList");
//            list.add(replaceStringMap);
//        }
//        {
//            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
//            replaceStringMap.put("List", "DiscussionInsertList");
//            list.add(replaceStringMap);
//        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("Mod", "ShieldSwitch");
            list.add(replaceStringMap);
        }

        for (ListOrderedMap<String, String> replaceStringMap : list) {
            List<String> paths = new ArrayList<>();
//            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleListReqForm.java");
//            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleListResBean.java");
//            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleListStrategy.java");
//            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleListStrategyImpl.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleModReqForm.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleModResBean.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleModStrategy.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/ArticleModStrategyImpl.java");

            paths.forEach(path -> replace(path, replaceStringMap));
        }



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
