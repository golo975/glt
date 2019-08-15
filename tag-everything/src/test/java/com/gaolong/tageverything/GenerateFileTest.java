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
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoListMy");
            replaceStringMap.put("videoList", "videoListMy");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoListWaitAudit");
            replaceStringMap.put("videoList", "videoListWaitAudit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoAddInit");
            replaceStringMap.put("videoList", "videoAddInit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoAdd");
            replaceStringMap.put("videoList", "videoAdd");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoModInit");
            replaceStringMap.put("videoList", "videoModInit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoMod");
            replaceStringMap.put("videoList", "videoMod");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoDel");
            replaceStringMap.put("videoList", "videoDel");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoAuditSubmit");
            replaceStringMap.put("videoList", "videoAuditSubmit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoAuditRefuse");
            replaceStringMap.put("videoList", "videoAuditRefuse");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoPublishInit");
            replaceStringMap.put("videoList", "videoPublishInit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoPublish");
            replaceStringMap.put("videoList", "videoPublish");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoOffline");
            replaceStringMap.put("videoList", "videoOffline");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoNavAddInit");
            replaceStringMap.put("videoList", "videoNavAddInit");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoNavAdd");
            replaceStringMap.put("videoList", "videoNavAdd");
            list.add(replaceStringMap);
        }
        {
            ListOrderedMap<String, String> replaceStringMap = new ListOrderedMap<>();
            replaceStringMap.put("VideoList", "VideoDownload");
            replaceStringMap.put("videoList", "videoDownload");
            list.add(replaceStringMap);
        }


        for (ListOrderedMap<String, String> replaceStringMap : list) {
            List<String> paths = new ArrayList<>();
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/VideoListReqForm.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/VideoListResBean.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/VideoListStrategy.java");
            paths.add("/Users/pxcm-0101-01-0128/IdeaProjects/media-server-mis/media-server-mis-admin/src/main/java/com/kr/media/mis/admin/krypton/widget/pattern/VideoListStrategyImpl.java");
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
