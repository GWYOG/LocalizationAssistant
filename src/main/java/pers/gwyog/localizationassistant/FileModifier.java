package pers.gwyog.localizationassistant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class FileModifier {

    /*
     * LA所有的功能都由此类的实例对象实现
     * 
     * @Author: JJN(GWYOG)
     */

    private MainFrame parentFrame;
    private FileInputStream fis;
    private InputStreamReader isr;
    private BufferedReader br;
    private File file;
    private FileOutputStream fos;
    private OutputStreamWriter osw;

    private String fileInput1;
    private String fileInput2;
    private String fileInput3;
    private String fileOutput;
    private String sOrigin;
    private String sTo;
    private String sInterval;
    private String sAdd;
    private String sRemoveFlag;
    private String[] keyFilter;
    private String[] rowNumberSplit;
    private String[] specialIgnoreSymbol;
    private String[] filter;
    private int updateType;

    // 用作全局变量
    private List<String> commentList = new LinkedList<>();

    public FileModifier(MainFrame parentFrame, int functionNumber, String fileInput, String fileOutput, String str1,
            String str2, String[] keyFilter, String[] rowNumberSplit, String[] specialIgnoreSymbol) {
        this.parentFrame = parentFrame;
        this.fileInput1 = fileInput;
        this.fileOutput = fileOutput;
        switch (functionNumber) {
        case 1:
            this.sOrigin = str1;
            this.sTo = str2;
            break;
        case 2:
            this.sInterval = str1;
            this.sAdd = str2;
            this.keyFilter = keyFilter;
            break;
        }
        this.rowNumberSplit = rowNumberSplit;
        this.specialIgnoreSymbol = specialIgnoreSymbol;
    }

    public FileModifier(MainFrame parentFrame, String fileInput1, String fileInput2, String fileInput3,
            String fileOutput, int updateType) {
        this.parentFrame = parentFrame;
        this.fileInput1 = fileInput1;
        this.fileInput2 = fileInput2;
        this.fileInput3 = fileInput3;
        this.fileOutput = fileOutput;
        this.updateType = updateType;
    }

    public FileModifier(MainFrame parentFrame, String fileInput1, String fileOutput, String sRemoveFlag, int flag) {
        this.parentFrame = parentFrame;
        this.fileInput1 = fileInput1;
        this.fileOutput = fileOutput;
        this.sRemoveFlag = sRemoveFlag;
        this.updateType = flag;
    }

    public FileModifier(MainFrame parentFrame, String fileInput1, String fileOutput, String[] filter) {
        this.parentFrame = parentFrame;
        this.fileInput1 = fileInput1;
        this.fileOutput = fileOutput;
        this.filter = filter;
    }

    // 将文件路径变为缓存文件路径
    public String dirToTempDir(String directory) {
        return directory.substring(0, directory.lastIndexOf('.')) + "-Temp.lang";
    }

    // 输入一个int型参数，判断它是否在用户给定的操作行数范围内：不在的话返回-1，在的话返回该行数，如果用户未置顶操作行数的话，默认对所有行操作，此时返回0
    public int verifyLineNumber(int lineNumber) {
        if (rowNumberSplit[0].equals("Blank"))
            return 0;
        else
            for (int index = 0; index <= rowNumberSplit.length - 1; index++) {
                try {
                    int index2 = rowNumberSplit[index].indexOf("-");
                    if (index2 == -1 && lineNumber == Integer.parseInt(rowNumberSplit[index])) {
                        return lineNumber;
                    } else if (index2 != -1
                            && lineNumber >= Integer.parseInt(rowNumberSplit[index].substring(0, index2))
                            && lineNumber <= Integer.parseInt(rowNumberSplit[index].substring(index2 + 1))) {
                        return lineNumber;

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "行数格式输入错误！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        return -1;
    }

    // 判断String类型的字符串能否转化为整数，如果能则返回该整数，不然则返回-1
    public int verifyNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数字格式输入错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    // 判断keyFilter数组中否含有有意义的元素
    public boolean isKeyFilterOn() {
        if (keyFilter == null || (keyFilter.length == 1 && keyFilter[0].equals("")))
            return false;
        else
            return true;
    }

    // 判断keyFilter数组中是否有字符串在字符串str中
    public boolean isKeyFilterInStr(String str) {
        // 未启用keyFilter表示不进行任何过滤，因此必须返回true
        if (!isKeyFilterOn())
            return true;
        else
            for (int i = 0; i < keyFilter.length; i++)
                if (str.indexOf(keyFilter[i]) != -1)
                    return true;
        return false;
    }

    // 判断一个字符串中是否包含中文
    public boolean isContainChineseChar(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fbf]");
        return pattern.matcher(str).find();
    }

    // 将字符串首字母变为大写
    public String toUpperInitial(String str) {
        if (str == null || str.isEmpty())
            return str;
        else
            return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // 将字符串首字母变为小写
    public String toLowerInitial(String str) {
        if (str == null || str.isEmpty())
            return str;
        else
            return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    // 判断字符串是否在字符串数组filter中
    public Boolean isInFilter(String str) {
        if (filter == null)
            return false;
        else
            for (int i = 0; i < filter.length; i++)
                if (filter[i].toLowerCase().equals(str))
                    return true;
        return false;
    }

    // 从List中删除全部指定字符串
    public void deleteElementFromList(String str, List<String> list) {
        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            if (iter.next().equals(str))
                iter.remove();
        }
    }

    // LA的第一个功能，条件替换
    public void functionConditionalReplace() {
        int lineNumber = 1;
        boolean outputTempFlag = false;
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput1.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && str.charAt(0) != '#' && verifyLineNumber(lineNumber) >= 0
                        && str.indexOf('=') != -1) {
                    str1 = str.substring(str.indexOf('=') + 1);
                    String str2, str3 = "";
                    int index, index2;
                    // 由于目前只支持使用一对替换忽略符，因此该数组的长度非2即1(即输入为空情形)
                    if (specialIgnoreSymbol.length == 2) {
                        char specialBegin = specialIgnoreSymbol[0].charAt(0);
                        char specialEnd = specialIgnoreSymbol[1].charAt(0);
                        while ((index = str1.indexOf(specialBegin)) != -1) {
                            str2 = str1.substring(0, index);
                            str3 = str3 + str2.replaceAll(sOrigin, sTo);
                            str1 = index == str1.length() ? "" : str1.substring(index + 1);
                            if ((index2 = str1.indexOf(specialEnd)) != -1) {
                                str3 = str3 + specialBegin + str1.substring(0, index2) + specialEnd;
                                str1 = index2 == str1.length() ? "" : str1.substring(index2 + 1);
                            } else {
                                str3 = str3 + specialBegin + str1.replaceAll(sOrigin, sTo);
                                str1 = "";
                            }
                        }
                        str3 = str3 + str1.replaceAll(sOrigin, sTo);
                    } else
                        str3 = str1.replaceAll(sOrigin, sTo);
                    osw.write(str.substring(0, str.indexOf('=') + 1) + str3);
                    osw.write("\n");
                } else {
                    osw.write(str);
                    osw.write("\n");
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return;
        } catch (IOException e) {
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                mw.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (IOException e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new MessageWindow(parentFrame, "错误！", "错误，未知错误！", -1);
            return;
        }
        new MessageWindow(parentFrame, "完成", "Done!", 1);
    }

    // LA的第二个功能，等距添加字符串
    public void functionAdd() {
        int lineNumber = 1;
        int iInterval;
        if ((iInterval = verifyNumber(sInterval)) == -1)
            return;
        boolean outputTempFlag = false;
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput1.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            // 用'='初始化，因为.lang文件中每行应该不会出现超过一个'='
            char specialBegin = '=';
            char specialEnd = '=';
            boolean ignoreFlag = true;
            // 由于目前只支持使用一对替换忽略符，因此该数组的长度非2即1(即输入为空情形)
            if (specialIgnoreSymbol.length == 2) {
                specialBegin = specialIgnoreSymbol[0].charAt(0);
                specialEnd = specialIgnoreSymbol[1].charAt(0);
            } else
                ignoreFlag = false;
            int lastCharCountDown = iInterval;
            int indexBegin = -1;
            int indexEnd = -1;
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && str.charAt(0) != '#' && verifyLineNumber(lineNumber) >= 0
                        && str.indexOf('=') != -1 && isKeyFilterInStr(str.substring(0, str.indexOf('=')))) {
                    // 开始操作
                    str1 = str.substring(str.indexOf('=') + 1);
                    String str2 = "";
                    while (!str1.equals("")) {
                        if (str1.length() > lastCharCountDown) {
                            if (ignoreFlag) {
                                indexBegin = str1.indexOf(specialBegin);
                                if (indexBegin == -1 || indexBegin + 1 > lastCharCountDown) {
                                    str2 = str2 + str1.substring(0, lastCharCountDown) + sAdd;
                                    str1 = str1.substring(lastCharCountDown);
                                    lastCharCountDown = iInterval;
                                } else {
                                    indexEnd = str1.indexOf(specialEnd);
                                    if (indexEnd != -1) {
                                        str2 = str2 + str1.substring(0, indexEnd + 1);
                                        lastCharCountDown -= indexBegin;
                                        str1 = str1.substring(indexEnd + 1);
                                    } else {
                                        str2 = str2 + str1.substring(0, lastCharCountDown) + sAdd;
                                        str1 = str1.substring(lastCharCountDown);
                                        lastCharCountDown = iInterval;
                                    }
                                }
                            } else {
                                str2 = str2 + str1.substring(0, lastCharCountDown) + sAdd;
                                str1 = str1.substring(lastCharCountDown);
                            }
                        } else if (str1.length() <= lastCharCountDown) {
                            str2 = str2 + str1;
                            str1 = "";
                        }
                    }
                    osw.write(str.substring(0, str.indexOf('=') + 1) + str2);
                    osw.write("\n");
                } else {
                    osw.write(str);
                    osw.write("\n");
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return;
        } catch (IOException e) {
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                mw.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (IOException e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new MessageWindow(parentFrame, "错误！", "错误，未知错误！", -1);
            return;
        }
        new MessageWindow(parentFrame, "完成", "Done!", 1);
    }

    // 删除指定文件
    public void fileDelete(String location) {
        File file = new File(location);
        if (file.exists())
            file.delete();
    }

    // LA的第三个功能，自动更新本地化文本
    public void functionUpdateLocalization() {
        String tempOutput;
        if (!fileOutput.isEmpty())
            tempOutput = fileOutput.substring(0, fileOutput.lastIndexOf('.'))
                    + "TempLocalizationAssistantUpdateLocalization.txt";
        else
            return;
        int status1 = 0, status2 = 0;
        int informationModeStatus = updateType % 10;
        int checkModeStatus = (updateType / 10) % 10;
        updateType = updateType / 100;
        switch (updateType) {
        case 1:
            status1 = functionUpdateLocalization(fileInput2, fileInput3, fileOutput);
            break;
        case 2:
            status2 = functionUpdateLocalization(fileInput1, fileInput2, fileInput3, fileOutput, 0, checkModeStatus,
                    informationModeStatus);
            break;
        case 3:
            status1 = functionUpdateLocalization(fileInput2, fileInput3, tempOutput);
            status2 = functionUpdateLocalization(fileInput1, fileInput2, tempOutput, fileOutput, 0, checkModeStatus,
                    informationModeStatus);
            fileDelete(tempOutput);
            break;
        case 4:
            status2 = functionUpdateLocalization(fileInput1, fileInput2, fileInput3, tempOutput, 0, checkModeStatus,
                    informationModeStatus);
            status1 = functionUpdateLocalization(fileInput2, tempOutput, fileOutput);
            fileDelete(tempOutput);
            break;
        case 5:
            status2 = functionUpdateLocalization(fileInput1, fileInput2, fileInput3, fileOutput, 1, checkModeStatus,
                    informationModeStatus);
            break;
        }
        LogWindow lw;
        if (status1 == 0 && status2 == 0) {
            if (commentList.size() > 0) {
                lw = new LogWindow("更新完毕&警告信息");
                lw.label.setText("<html><body>更新完毕！但LA检测到旧版zh_CN.lang中有新版en_US.lang中没有的" + commentList.size()
                        + "处注释，<br/>这些内容未被更新，但可能是之前汉化者留下的提示信息，请手动校验它们。</body></html>");
                for (String comment : commentList)
                    lw.textArea.append(comment + "\n");
            } else
                new MessageWindow(parentFrame, "完成", "Done!", 1);
        }
    }

    // 功能三重载方法1
    public int functionUpdateLocalization(String fileInput2, String fileInput3, String fileOutput) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        String str = "";
        String str1 = "";
        String str2 = "";
        boolean outputTempFlag = false;
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        try {
            fis = new FileInputStream(fileInput2);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && !str.startsWith("#") && !str.startsWith("//") && str.indexOf('=') != -1) {
                    // hashtable存放旧版zh_CN.lang的旧版键-旧版中文值
                    str1 = str.substring(0, str.indexOf('=')).toLowerCase();
                    str2 = str.substring(str.indexOf('=') + 1);
                    hashtable.put(str1, str2);
                } else if (str.length() != 0 && (str.startsWith("#") || str.startsWith("//")))
                    commentList.add(str);
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        try {
            fis = new FileInputStream(fileInput3);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput3.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && !str.startsWith("#") && !str.startsWith("//") && str.indexOf('=') != -1) {
                    // 判断新版en_US.lang中新版键是否和hashtable中的旧版键一样.
                    // 如果一样，则用旧版的中文值替换新版的英文值
                    str1 = str.substring(0, str.indexOf('='));
                    if (hashtable.containsKey(str1.toLowerCase()))
                        str1 = str1 + "=" + hashtable.get(str1.toLowerCase());
                    else
                        str1 = str;
                    osw.write(str1);
                    osw.write("\n");
                } else {
                    if (str.length() != 0 && (str.startsWith("#") || str.startsWith("//")))
                        deleteElementFromList(str, commentList);
                    osw.write(str);
                    osw.write("\n");
                }
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            mw.close();
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (Exception e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        return 0;
    }

    // 功能三重载方法2
    public int functionUpdateLocalization(String fileInput1, String fileInput2, String fileInput3, String fileOutput,
            int doubleCheckFlag, int checkModeStatus, int informationModeStatus) {
        Hashtable<String, String> hashtable1 = new Hashtable<String, String>();
        Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
        Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
        String str = "";
        String str1 = "";
        String str2 = "";
        String str3 = ""; // output String
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        try {
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && !str.startsWith("#") && !str.startsWith("//") && str.indexOf('=') != -1) {
                    // hashtable1:英文文本旧版小写键-旧版小写英文值
                    str1 = str.substring(0, str.indexOf('=')).toLowerCase();
                    str2 = str.substring(str.indexOf('=') + 1);
                    hashtable1.put(str1, str2.toLowerCase());
                }
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        try {
            fis = new FileInputStream(fileInput2);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && !str.startsWith("#") && !str.startsWith("//") && str.indexOf('=') != -1
                        && str.indexOf('=') != str.length() - 1) {
                    // hashtable2:旧版中文文本小写键-值
                    str1 = str.substring(0, str.indexOf('=')).toLowerCase();
                    str2 = str.substring(str.indexOf('=') + 1);
                    hashtable2.put(str1, str2);
                } else if ((updateType == 2 || updateType == 5) && str.length() != 0
                        && (str.startsWith("#") || str.startsWith("//")))
                    commentList.add(str);
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        // hashtable:旧版英文值-旧版中文值，这里认为旧版英文键内部或中文键内部不会出现大小写不同的键
        Set<String> keys1 = hashtable1.keySet();
        for (String key : keys1)
            if (hashtable2.containsKey(key))
                if (checkModeStatus == 1 && hashtable3.containsKey(hashtable1.get(key))
                        && !hashtable2.get(key).equals(hashtable3.get(hashtable1.get(key))))
                    hashtable3.put(hashtable1.get(key), "##Warning:" + hashtable2.get(key));
                else
                    hashtable3.put(hashtable1.get(key), hashtable2.get(key));
        boolean outputTempFlag = false;
        try {
            fis = new FileInputStream(fileInput3);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput3.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && !str.startsWith("#") && !str.startsWith("//") && str.indexOf('=') != -1) {
                    if (doubleCheckFlag == 0) {
                        str1 = str.substring(0, str.indexOf('=')); // key
                        str2 = str.substring(str.indexOf('=') + 1); // value
                        // 若未发现新版某行与旧版有相同的英文值，则str3仍未该行；若发现与旧版有相同的值，则将=后替换为旧版中文值；特别地，若开启校验模式，则当该英文值对应多个不同旧版中文值时，还要在str3前面加上##Warning:
                        if (hashtable3.containsKey(str2.toLowerCase()))
                            if (hashtable3.get(str2.toLowerCase()).indexOf("##Warning:") == -1)
                                str3 = str.substring(0, str.indexOf('=') + 1) + hashtable3.get(str2.toLowerCase());
                            else
                                str3 = "##Warning:" + str.substring(0, str.indexOf('=') + 1)
                                        + hashtable3.get(str2.toLowerCase()).replaceAll("##Warning:", "");
                        else
                            str3 = str;
                        // 当勾选显示变更信息后，还要对str3进一步处理才能输出到文件中
                        // str3和str1忽略大小写相等意味着该行英文值在旧版中不存在.此时需要显示变更信息
                        if (informationModeStatus == 1 && str3.toLowerCase().equals(str.toLowerCase()))
                            // 之后再判断该行英文键是否在旧版中存在，若存在，则说明该行文本更新了
                            if (hashtable2.containsKey(str1.toLowerCase())
                                    && !hashtable2.get(str1.toLowerCase()).toLowerCase().equals(str2.toLowerCase())) {
                            osw.write("##-:" + str1 + "=" + hashtable1.get(str1.toLowerCase()));
                            osw.write("\n");
                            osw.write("##+:" + str3);
                            osw.write("\n");
                            osw.write("##R:" + str1 + "=" + hashtable2.get(str1.toLowerCase()));
                            osw.write("\n");
                            } else {
                            osw.write("##N:" + str);
                            osw.write("\n");
                            }
                        else {
                            osw.write(str3);
                            osw.write("\n");
                        }
                    } else if (doubleCheckFlag == 1) {
                        if (informationModeStatus == 0) {
                            str1 = str.substring(str.indexOf('=') + 1);
                            str2 = str.substring(0, str.indexOf('='));
                            if (hashtable3.containsKey(str1.toLowerCase()) && hashtable1.containsKey(str2.toLowerCase())
                                    && hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase()))
                                str3 = str2 + "=" + hashtable3.get(str1.toLowerCase());
                            else
                                str3 = str;
                            osw.write(str3);
                            osw.write("\n");
                        } else {
                            str1 = str.substring(str.indexOf('=') + 1);
                            str2 = str.substring(0, str.indexOf('='));
                            if (hashtable3.containsKey(str1.toLowerCase()) && hashtable1.containsKey(str2.toLowerCase())
                                    && hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase())) {
                                str1 = str2 + "=" + hashtable3.get(str1.toLowerCase());
                                osw.write(str1);
                                osw.write("\n");
                            } else if (hashtable3.containsKey(str1.toLowerCase())
                                    && hashtable1.containsKey(str2.toLowerCase())
                                    && !hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase())) {
                                osw.write("##-:" + str2 + "=" + hashtable1.get(str2.toLowerCase()));
                                osw.write("\n");
                                osw.write("##+:" + str);
                                osw.write("\n");
                                osw.write("##R:" + str2 + "=" + hashtable3.get(str1.toLowerCase()));
                                osw.write("\n");
                            } else {
                                osw.write("##N:" + str);
                                osw.write("\n");
                            }
                        }
                    }
                } else {
                    if ((updateType == 2 || updateType == 5) && str.length() != 0
                            && (str.startsWith("#") || str.startsWith("//")))
                        deleteElementFromList(str, commentList);
                    osw.write(str);
                    osw.write("\n");
                }
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            mw.close();
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (Exception e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        return 0;
    }

    // LA的第四个功能，逐行条件清理
    public int functionConditionalRemove() {
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        int updateType1 = updateType / 10;
        int updateType2 = updateType % 10;
        boolean outputTempFlag = false;
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput1.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && (str.startsWith("##") || str.charAt(0) != '#') && str.indexOf('=') != -1) {
                    if (updateType2 == 1) {
                        Pattern pattern = Pattern.compile("##[a-zA-Z]+:");
                        Matcher matcher = pattern.matcher(str);
                        if (matcher.find())
                            str = str.substring(matcher.end());
                    }
                    if (updateType1 == 0)
                        if (str.length() != 0 && !sRemoveFlag.equals("") && str.indexOf(sRemoveFlag) != -1)
                            str1 = str.substring(0, str.indexOf(sRemoveFlag));
                        else
                            str1 = str;
                    else if (str.length() != 0 && !sRemoveFlag.equals("") && str.indexOf(sRemoveFlag) != -1
                            && (str.indexOf(sRemoveFlag) + sRemoveFlag.length() < str.length()))
                        str1 = str.substring(0, str.indexOf(sRemoveFlag) + sRemoveFlag.length());
                    else
                        str1 = str;

                } else
                    str1 = str;
                osw.write(str1);
                osw.write("\n");
            }
        } catch (FileNotFoundException e) {
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                mw.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (IOException e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new MessageWindow(parentFrame, "错误！", "错误，未知错误！", -1);
            return -1;
        }
        new MessageWindow(parentFrame, "完成", "Done!", 1);
        return 0;
    }

    // LA的第5个功能，已译文本替换
    public int functionAutoReplaceEnglishTextWithChineseTranslation() {
        Hashtable<String, String> hashtable1 = new Hashtable<String, String>();
        Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
        Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
        int updateType = this.updateType / 10;
        int checkModeStatus = this.updateType % 10;
        String str = "";
        String str1 = "";
        String str2 = "";
        String str3 = ""; // output String
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        // 载入第一个文件
        try {
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && str.charAt(0) != '#' && str.indexOf('=') != -1) {
                    if (updateType == 0) {
                        // hashtable1:英文文本小写键-小写英文值
                        str1 = str.substring(0, str.indexOf('=')).toLowerCase();
                        str2 = str.substring(str.indexOf('=') + 1);
                        hashtable1.put(str1, str2.toLowerCase());
                    } else if (updateType == 1) {
                        // hashtable3:英文值-中文值
                        str1 = str.substring(0, str.indexOf('='));
                        str2 = str.substring(str.indexOf('=') + 1);
                        if (hashtable3.containsKey(str1) && !hashtable3.get(str1).equals(str2))
                            hashtable3.put(str1, "##Warnging:" + str2);
                        else
                            hashtable3.put(str1, str2);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        // 当以执行默认模式时，载入第二个文件并创建hashtable3
        if (updateType == 0) {
            try {
                fis = new FileInputStream(fileInput2);
                isr = new InputStreamReader(fis, "UTF-8");
                br = new BufferedReader(isr);
                while ((str = br.readLine()) != null) {
                    if (str.length() != 0 && str.charAt(0) != '#' && str.indexOf('=') != -1
                            && str.indexOf('=') != str.length() - 1) {
                        // hashtable2:中文文本小写键-值(这儿需要判断它里面是否包含汉字,不包含汉字的值略过)
                        str1 = str.substring(0, str.indexOf('=')).toLowerCase();
                        str2 = str.substring(str.indexOf('=') + 1);
                        if (isContainChineseChar(str2))
                            hashtable2.put(str1, str2);
                    }
                }
            } catch (FileNotFoundException e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
                return -1;
            } catch (IOException e) {
                new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
                return -1;
            } finally {
                try {
                    br.close();
                    isr.close();
                    fis.close();
                } catch (Exception e) {
                    mw.close();
                    new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                    return -1;
                }
            }
            // hashtable3:已译英文值-中文值，这里认为英文键内部或中文键内部不会出现大小写不同的键
            Set<String> keys1 = hashtable1.keySet();
            for (String key : keys1)
                if (hashtable2.containsKey(key))
                    if (checkModeStatus == 1 && hashtable3.containsKey(hashtable1.get(key))
                            && !hashtable2.get(key).equals(hashtable3.get(hashtable1.get(key))))
                        hashtable3.put(hashtable1.get(key), "##Warning:" + hashtable2.get(key));
                    else
                        hashtable3.put(hashtable1.get(key), hashtable2.get(key));
        }
        // 读取文件3，进行操作并输入到文件4
        boolean outputTempFlag = false;
        try {
            fis = new FileInputStream(fileInput3);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput3.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && str.charAt(0) != '#' && str.indexOf('=') != -1) {
                    str1 = str.substring(0, str.indexOf('='));
                    str2 = str.substring(str.indexOf('=') + 1);
                    int warningFlag = 0;
                    String strTemp;
                    for (String key : hashtable3.keySet()) {
                        str3 = toLowerInitial(key);
                        if (str2.indexOf(str3) != -1) {
                            strTemp = hashtable3.get(key);
                            if (strTemp.indexOf("##Warning:") == -1)
                                str2 = str2.replaceAll(str3, strTemp);
                            else {
                                warningFlag = 1;
                                str2 = str2.replaceAll(str3, strTemp.replace("##Warning:", ""));
                            }
                        }
                        str3 = toUpperInitial(key);
                        if (str2.indexOf(str3) != -1) {
                            strTemp = hashtable3.get(key);
                            if (strTemp.indexOf("##Warning:") == -1)
                                str2 = str2.replaceAll(str3, strTemp);
                            else {
                                warningFlag = 1;
                                str2 = str2.replaceAll(str3, strTemp.replace("##Warning:", ""));
                            }
                        }
                    }
                    str = str1 + "=" + str2;
                    str = warningFlag == 0 ? str : "##Warnging:" + str;
                }
                osw.write(str);
                osw.write("\n");
            }
        } catch (FileNotFoundException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                mw.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (Exception e) {
                mw.close();
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            mw.close();
            new MessageWindow(parentFrame, "错误！", "错误，未知错误！", -1);
            return -1;
        }
        new MessageWindow(parentFrame, "完成", "Done!", 1);
        return 0;
    }

    // LA的第6个功能，统计高频词汇
    public int functionWordCount() {
        // 读取文件，进行操作并输入到文件
        Map<String, Integer> map = new HashMap<String, Integer>();
        String str = "";
        String str1 = "";
        boolean outputTempFlag = false;
        MessageWindow mw = new MessageWindow(parentFrame, "处理中", "", 0);
        try {
            fis = new FileInputStream(fileInput1);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            if (fileInput1.equals(fileOutput)) {
                file = new File(dirToTempDir(fileOutput));
                outputTempFlag = true;
            } else
                file = new File(fileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            while ((str = br.readLine()) != null) {
                if (str.length() != 0 && str.charAt(0) != '#' && str.indexOf('=') != -1) {
                    String[] sArrayTemp;
                    str1 = str.substring(str.indexOf('=') + 1);
                    sArrayTemp = str1.split(" ");
                    if (sArrayTemp == null)
                        continue;
                    for (int i = 0; i < sArrayTemp.length; i++) {
                        String sTemp = sArrayTemp[i].toLowerCase();
                        sTemp = sTemp.replaceAll("\\.", "");
                        sTemp = sTemp.replaceAll(",", "");
                        sTemp = sTemp.replaceAll("\"", "");
                        if (isInFilter(sTemp))
                            continue;
                        if (map.containsKey(sTemp))
                            map.put(sTemp, map.get(sTemp) + 1);
                        else
                            map.put(sTemp, 1);
                    }
                }
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return (o2.getValue() - o1.getValue());
                }
            });
            for (int i = 0; i < list.size(); i++) {
                osw.write(list.get(i).toString());
                osw.write("\n");
            }
        } catch (FileNotFoundException e) {
            new MessageWindow(parentFrame, "错误！", "错误，找不到指定文件！", -1);
            return -1;
        } catch (IOException e) {
            new MessageWindow(parentFrame, "错误！", "错误，读取文件失败", -1);
            return -1;
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                osw.close();
                fos.close();
                mw.close();
                if (outputTempFlag) {
                    fileDelete(fileOutput);
                    file.renameTo(new File(fileOutput));
                }
            } catch (IOException e) {
                new MessageWindow(parentFrame, "错误！", "错误，关闭数据流失败！", -1);
                return -1;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new MessageWindow(parentFrame, "错误！", "错误，未知错误！", -1);
            return -1;
        }
        new MessageWindow(parentFrame, "完成", "Done!", 1);
        return 0;
    }

}
