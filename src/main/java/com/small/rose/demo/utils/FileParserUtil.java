package com.small.rose.demo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ FileParserUtil ] 说明：无
 * @Function :  功能说明：移除 java  html  js 文件里的注释  行注释、跨行注释 等
 * @Date ：2024/7/10 16:36
 * @Version ： 1.0
 **/
public class FileParserUtil {


    public static List<String>  readFile(String filePath) throws IOException {
        //StringBuilder contentBuilder = new StringBuilder();
        List<String> strList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        FileType fileType = getFileType(filePath);
        boolean inBlockComment = false;

        while ((line = reader.readLine()) != null) {
            if (inBlockComment) {
                int endIndex = line.indexOf("*/");
                if (endIndex >= 0) {
                    inBlockComment = false;
                    line = line.substring(endIndex + 2);
                } else {
                    line = "";
                }
            }

            if (fileType == FileType.JAVA || fileType == FileType.JAVASCRIPT) {
                int singleLineCommentIndex = line.indexOf("//");
                if (singleLineCommentIndex >= 0) {
                    line = line.substring(0, singleLineCommentIndex);
                }
            }

            if (fileType == FileType.JAVA || fileType == FileType.JAVASCRIPT) {
                int startIndex = line.indexOf("/*");
                while (startIndex >= 0 && !inBlockComment) {
                    int endIndex = line.indexOf("*/", startIndex + 2);
                    if (endIndex >= 0) {
                        line = line.substring(0, startIndex) + line.substring(endIndex + 2);
                    } else {
                        line = line.substring(0, startIndex);
                        inBlockComment = true;
                        break;
                    }
                    startIndex = line.indexOf("/*", startIndex + 2);
                }
            } else if (fileType == FileType.HTML) {
                int startIndex = line.indexOf("<!--");
                while (startIndex >= 0 && !inBlockComment) {
                    int endIndex = line.indexOf("-->", startIndex + 4);
                    if (endIndex >= 0) {
                        line = line.substring(0, startIndex) + line.substring(endIndex + 3);
                    } else {
                        line = line.substring(0, startIndex);
                        inBlockComment = true;
                        break;
                    }
                    startIndex = line.indexOf("<!--", startIndex + 4);
                }
            }

            if (!line.isEmpty()) {
                //contentBuilder.append(line).append("\n");
                strList.add(line);
            }
            inBlockComment = checkBlockComment(line, fileType, inBlockComment);
        }

        reader.close();
        //return contentBuilder.toString();
        return strList ;
    }


    private static boolean checkBlockComment(String line, FileType fileType, boolean inBlockComment) {
        if (inBlockComment) {
            int endIndex = line.indexOf("*/");
            if (endIndex >= 0) {
                inBlockComment = false;
                line = line.substring(endIndex + 2);
            } else {
                line = "";
            }
        }

        return inBlockComment;
    }

    private static FileType getFileType(String filePath) {
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1);
        switch (extension) {
            case "java":
                return FileType.JAVA;
            case "html":
                return FileType.HTML;
            case "js":
                return FileType.JAVASCRIPT;
            default:
                return FileType.UNKNOWN;
        }
    }

    private enum FileType {
        JAVA,
        HTML,
        JAVASCRIPT,
        UNKNOWN
    }
}
