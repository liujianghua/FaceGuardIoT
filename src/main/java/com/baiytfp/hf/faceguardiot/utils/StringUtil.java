package com.baiytfp.hf.faceguardiot.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }
    
    /**
     * 把指定的字符串向左边补充足够位数的字符
     * @param source
     * @param delim
     * @return
     */
    public static String leftLengthString(String source, int len) {
    	return leftLengthString(source, len, '0');
    }
    
    /**
     * 把指定的字符串向左边补充足够位数的字符
     * @param source
     * @param delim
     * @return
     */
    public static String leftLengthString(String source, int len, char c) {
    	int n = len - source.length();
    	StringBuffer sChar = new StringBuffer();
    	for(int i = 0; i < n; i++){
    		sChar.append(c);
    	}
    	return sChar.toString() + source;
    }
    
    /**
     * 把指定的字符串按分隔符产生数组
     * @param source
     * @param delim
     * @return
     */
    public static String[] split(String source, String delim) {
        String wordLists[];
        if (source == null) {
            wordLists = new String[1];
            wordLists[0] = source;
            return wordLists;
        }
        if (delim == null) {
            delim = ",";
        }
        StringTokenizer st = new StringTokenizer(source, delim, true);
        int total = st.countTokens();
        String tempList[] = new String[total + 1];
        boolean preFlag = true;
        int j = 0;
        for (int i = 0; i < total; i++) {
            tempList[i] = st.nextToken();
            if (tempList[i].equals(delim) && preFlag) {
                tempList[i] = "";
                j++;
                if (i >= total - 1) {
                    tempList[i + 1] = "";
                    j++;
                }
            } else
            if (tempList[i].equals(delim)) {
                preFlag = true;
                if (i >= total - 1) {
                    tempList[i] = "";
                    j++;
                }
                total--;
                i--;
            } else {
                preFlag = false;
                j++;
            }
        }

        wordLists = new String[j];
        for (int i = 0; i < j; i++) {
            wordLists[i] = tempList[i];
        }

        return wordLists;
    }

    public static String[] split(String source, char delim) {
        return split(source, String.valueOf(delim));
    }

    /**
     * 对指定的字符串转为整型
     * @param input
     * @param deft
     * @return
     */
    public static int getInt(String input, int deft) {
        int iRet = deft;
        try {
            String temp = getString(input);
            if (!temp.equals("")) {
                iRet = Integer.parseInt(temp);
            }
        } catch (NumberFormatException fe) {
            fe.printStackTrace();
        }
        return iRet;
    }

    public static int getInt(int input,int deft){
      if(input == 0)
        return deft;
      else
        return input;
    }

    /**
	 * 对指定的字符串转为长整型
	 * 
	 * @param input
	 * @param deft
	 * @return
	 */
	public static long getLong(String input, long deft) {
		long iRet = deft;
		try {
			String temp = getString(input);
			if (!temp.equals("")) {
				iRet = Long.parseLong(temp);
			}
		} catch (NumberFormatException fe) {
			fe.printStackTrace();
		}
		return iRet;
	}

	public static long getLong(String input) {
		return getLong(input, 0);
	}
	
    /**
     * 把字符性的数字转换成double型
     * @param value
     * @return
     */
    public static double getDouble(String value) {
        double dRet = 0.0D;
        try {
            dRet = Double.parseDouble(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dRet;
    }

    /**
     * 取得带有默认值的字符串
     * @param obj
     * @param deft
     * @return
     */
    public static String getString(Object obj, String deft) {
        String temp = "";
        if (obj == null) {
            temp = "";
        } else {
            temp = (obj + "").trim();
        }

        if (temp.equals("null") || temp.equals("")) {
            temp = deft;
        }
        return temp;
    }

    public static String getString(Object obj) {
        return getString(obj, "");
    }
    
    public static String getSubString(Object obj, int len) {
        String temp = "";
        if (obj == null) {
            temp = "";
        } else {
            temp = (obj + "").trim();
        }

        if (temp.equals("null") || temp.equals("")) {
            temp = "";
        }

        try {
            if (temp.length() > len) {
                temp = temp.substring(0, len);
            }
        } catch (StringIndexOutOfBoundsException ox) {
            ox.printStackTrace();
        }
        return temp;
    }

    /**
     * 把空串转为指定的字符串
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String ifNull(Object obj, Object defaultValue) {
        String temp = "";
        if (obj == null) {
            temp = "";
        } else {
            temp = (obj + "").trim();
        }

        if (temp.equals("") || temp.equals("null")) {
            temp = defaultValue + "";
        }

        return temp;
    }
    
    /**
     * 检查是否空字符串
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean isNull(Object obj) {
        String temp = "";
        if (obj == null) {
            temp = "";
        } else {
            temp = (obj + "").trim();
        }

        if (temp.equals("") || temp.equals("null")) {
            return true;
        }else{
        	return false;
        }
    }

    /**
     * 把ArrayList对象的值转为指定分隔符的字符串
     * @param chrList
     * @param splitTag
     * @return
     */
    public static String toString(ArrayList chrList, String splitTag) {
        return toString(chrList, splitTag, true);
    }

    public static String toString(String[] chrList, String splitTag) {
        return toString(chrList, splitTag, true);
    }

    public static String toString(ArrayList chrList, String splitTag,
                                  boolean isChar) {
        String str = "";
        if (chrList != null) {
            for (int i = 0; i < chrList.size(); i++) {
                if (isChar) {
                    str += "'" + chrList.get(i) + "'" + splitTag;
                } else {
                    str += chrList.get(i) + splitTag;
                }
            }
            if (str.endsWith(splitTag)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String toString(String[] chrList, String splitTag,
                                  boolean isChar) {
        String str = "";
        if (chrList != null) {
            for (int i = 0; i < chrList.length; i++) {
                if (isChar) {
                    str += "'" + chrList[i] + "'" + splitTag;
                } else {
                    str += chrList[i] + splitTag;
                }
            }
            if (str.endsWith(splitTag)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 把回车，换行，空格符转为HTML格式字符
     * @param str
     * @return
     */
    public static String HTMLTurn(String str) {
        if ((null == str) || (str.trim().equals(""))) {
            return "";
        } while (str.indexOf("\n") != -1) {
            str = str.substring(0, str.indexOf("\n")) + "<br>&nbsp;" +
                  str.substring(str.indexOf("\n") + 1);
        } while (str.indexOf(" ") != -1) {
            str = str.substring(0, str.indexOf(" ")) + "&nbsp;" +
                  str.substring(str.indexOf(" ") + 1);
        }
        return str;
    }

    /**
     * 把特殊字符转为HTML格式字符
     * @param as_Value
     * @return
     * @throws Exception
     */
    public static String htmEncode(String as_Value) throws Exception {
        StringBuffer ls_Out = new StringBuffer();
        try {
            String ls_Value = as_Value;
            if (ls_Value != null) {
                for (int i = 0; i < ls_Value.length(); i++) {
                    ls_Out.append(emitQuote(ls_Value.charAt(i)));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ls_Out.toString();
    }

    private static String emitQuote(char c) throws Exception {
        String ls_Out = null;
        try {
            if (c == '&') {
                ls_Out = "&amp;";
            } else
            if (c == '"') {
                ls_Out = "&quot;";
            } else
            if (c == '<') {
                ls_Out = "&lt;";
            } else
            if (c == '>') {
                ls_Out = "&gt;";
            } else {
                ls_Out = String.valueOf(c);
            }
        } catch (Exception e) {
            throw e;
        }
        return ls_Out;
    }
    
    /**
     * 分割并取得里面内容
     * @param aSrc  源字符串
     * @param aDelim  分割符
     * @param aFieldNum 取第几部分
     * @return
     */
    public static String getField(String aSrc, String aDelim, long aFieldNum) {
        if (aSrc == null)
          return null;

        int beginIndex = 0;
        int endIndex = 0;
        int fieldNum = 0;

        while (aSrc.indexOf(aDelim, endIndex) != -1) {
          endIndex = aSrc.indexOf(aDelim, endIndex);
          if (fieldNum == aFieldNum)
            break;

          ++endIndex;
          beginIndex = endIndex;
          ++fieldNum;
        }
        if ((beginIndex == endIndex) && ((
          (aSrc.indexOf(aDelim, 0) == -1) || (aSrc.indexOf(aDelim, endIndex) == -1))))
          endIndex = aSrc.length();

        if (endIndex != -1)
          return aSrc.substring(beginIndex, endIndex);

        return aSrc.substring(beginIndex);
      }
    
    public static long getLongVal(Long l) {
    	if(l != null){
    		return l.longValue();
    	}else{
    		return 0;
    	}
    }
    
    public static String getMapString(Map obj, String keyString, String deft) {
    	if(obj != null){
    		return getString(obj.get(keyString), deft);
    	}else{
    		return deft;
    	}
	}
	public static String getMapString(Map obj, String keyString) {
		if(obj != null){
			return getString(obj.get(keyString), "");
		}else{
			return "";
		}
	}
	
	//过滤html字符
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		java.util.regex.Matcher m_script;
		Pattern p_style;
		java.util.regex.Matcher m_style;
		Pattern p_html;
		java.util.regex.Matcher m_html;

		Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
}
