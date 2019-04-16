/**
 * 65. 有效数字
 *
 * 验证给定的字符串是否可以解释为十进制数字。
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 */
public class LeetCode65 {
    /**
     * 用动态规划的思路尝试一下
     * 但是这题扯淡 " .1e3" "3."都合法 "6e6.5" 不合法
     * 真特么无聊 调了11次 终于知道作者认为什么样的数是合法的了
     * 这个方法的代码已经在调试后乱了逻辑
     * 可以看下面的代码简化版 但是效率会稍稍差点
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        s = s.trim();
        if(s == null || s.length() == 0){
            return false;
        }
        char[] chars = s.toCharArray();
        boolean[] dp = new boolean[s.length()];
        boolean blankSpace = false;         //空格连续状态
        boolean sign = false;               //正负号出现过
        boolean decimal = false;            //小数点出现过
        boolean num = false;                //数字出现过
        boolean e = false;                  //e指数出现过
        boolean end = false;                //结尾空格
        for(int i = 0; i < s.length(); i++){
            if(i == 0){
                //第一个字符可以为 空格 正负号 数字
                if(chars[i] == ' '){
                    dp[i] = false;
                    blankSpace = true;
                }else if(chars[i] == '-' || chars[i] == '+'){
                    dp[i] = false;
                    sign = true;
                    blankSpace = false;
                }else if(isNum(chars[i])){
                    dp[i] = true;
                    num = true;
                    blankSpace = false;
                }else if(chars[i] == '.'){
                    dp[i] = false;
                    decimal = true;
                    blankSpace = false;
                }else {
                    return false;
                }
            }else {
                if(chars[i] == ' '){
                    //如果为空格 必须为连续空格
                    if(blankSpace){
                        dp[i] = false;
                    }else {
                        if(dp[i-1]){
                            if(chars[i-1] == ' ' || isNum(chars[i-1])){
                                dp[i] = true;
                                end = true;
                                continue;
                            }
                        }
                        return false;
                    }
                }else if(chars[i] == '-' || chars[i] == '+'){
                    //符号出现时 要么是连续空格状态 要么是早于小数点、数字、指数出现 要么在e出现后再出现
                    if(blankSpace && !sign && !end){
                        dp[i] = false;
                        sign = true;
                        blankSpace = false;
                    }else{
                        if(end){
                            return false;
                        }
                        if(sign){
                            return false;
                        }
                        if(!e){
                            return false;
                        }
                        if(chars[i - 1] != 'e'){
                            return false;
                        }
                        sign = true;
                        dp[i] = false;
                    }
                }else if(chars[i] == '.'){
                    if(e){
                        return false;
                    }
                    if(!decimal && !end){
                        if(!e && !decimal){
                            dp[i] = true;
                        }else {
                            if(e && chars[i-1] == 'e'){
                                return false;
                            }
                            dp[i] = false;
                        }
                        decimal = true;
                        blankSpace = false;
                    }else {
                        return false;
                    }
                }else if(chars[i] == 'e'){
                    if(e){
                        return false;
                    }
                    if(!blankSpace && !e && num && !end){
                        if(chars[i-1] == '-' ||  chars[i-1] == '+'){
                            return false;
                        }
                        dp[i] = false;
                        e = true;
                        sign = false;
                        decimal = false;
                        blankSpace = false;
                    }else if(end){
                        return false;
                    }else if(!num){
                        return false;
                    }
                }else if(isNum(chars[i])){
                    if(end){
                        return false;
                    }
                    dp[i] = true;
                    num = true;
                    blankSpace = false;
                }else {
                    return false;
                }
            }
        }
        if(!num){
            return false;
        }
        return dp[s.length() - 1];
    }

    private boolean isNum(char c){
        if(c - '0' >= 0 && c - '0' <= 9){
            return true;
        }
        return false;
    }

    /**
     * 不要效率 要思路清晰重写一遍
     * @param s
     * @return
     */
    public boolean isNumber2(String s) {
        if(s == null){
            return false;
        }
        s = s.trim();
        if(s.length() == 0){
            return false;
        }
        int index = s.indexOf('e');
        if(index != -1 && index == s.lastIndexOf('e')){
            //有e存在
            String[] strs = s.split("e");
            //必须两边都非空 1e 的分割strs长度为1
            if(strs == null || strs.length != 2 || strs[0] == null || strs[0].length() == 0 || strs[1] == null || strs[1].length() == 0){
                return false;
            }
            if(!isDotNum(strs[0])){
                return false;
            }
            char[] chars1 = strs[1].toCharArray();
            if(chars1[0] == '-' || chars1[0] == '+'){
                return isNum(chars1, 1);
            }else {
                return isNum(chars1, 0);
            }
        }else {
            return isDotNum(s);
        }
    }

    private boolean isDotNum(String s){
        //没有e 注意-.1和-1.都是合法的
        int index = s.indexOf('.');
        if(index != -1 && s.lastIndexOf('.') == index){
            //包含'.'
            String[] strs = s.split("\\.");
            if(strs == null || strs.length == 0){
                return false;
            }
            //1.的数组为len为1
            //strs[0]可以为"-1" "-" "1" strs[1]只能是空或者整数
            if(strs.length == 1){
                char[] chars = strs[0].toCharArray();
                if(chars[0] == '-' || chars[0] == '+'){
                    return isNum(chars, 1);
                }else {
                    return isNum(chars, 0);
                }
            }
            if((strs[0] == null || strs[0].length() == 0) && (strs[1] == null || strs[1].length() == 0)){
                return false;
            }else if(strs[0] == null || strs[0].length() == 0){
                //strs[1] 必须为数字 .1
                return isNum(strs[1].toCharArray(), 0);
            }else if(strs[1] == null || strs[1].length() == 0){
                //-1. 1.
                char[] chars = strs[0].toCharArray();
                if(chars[0] == '-' || chars[0] == '+'){
                    return isNum(chars, 1);
                }else {
                    return isNum(chars, 0);
                }
            }else {
                //都不空 -1.1 1.1这种
                char[] chars0 = strs[0].toCharArray();
                char[] chars1 = strs[1].toCharArray();
                if(chars0[0] == '-' || chars0[0] == '+'){
                    if(chars0.length == 1){
                        return isNum(chars1, 0);
                    }else {
                        return isNum(chars0, 1) && isNum(chars1, 0);
                    }
                }else {
                    return isNum(chars0, 0) && isNum(chars1, 0);
                }
            }
        }else {
            //不包含点 只能是 1 -1 +1这种
            char[] chars = s.toCharArray();
            if(chars[0] == '-' || chars[0] == '+'){
                return isNum(chars, 1);
            }else {
                return isNum(chars, 0);
            }
        }
    }

    private boolean isNum(char[] chars, int index){
        if(index >= chars.length){
            return false;
        }
        for(int i = index; i < chars.length; i++){
            if(chars[i] - '0' < 0 || chars[i] - '0' > 9){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        LeetCode65 leetCode65 = new LeetCode65();
        System.out.println("32 " + "=>\t" + leetCode65.isNumber2("32"));
        System.out.println("0 " + "=>\t" + leetCode65.isNumber2("0"));
        System.out.println("0.1 " + "=>\t" + leetCode65.isNumber2(" 0.1 "));
        System.out.println("abc" + "=>\t" + leetCode65.isNumber2("abc"));
        System.out.println("1 a" + "=>\t" + leetCode65.isNumber2("1 a"));
        System.out.println("2e10" + "=>\t" + leetCode65.isNumber2("2e10"));
        System.out.println(" -90e3   " + "=>\t" + leetCode65.isNumber2(" -90e3   "));
        System.out.println(" 1e" + "=>\t" + leetCode65.isNumber2(" 1e"));
        System.out.println("e3" + "=>\t" + leetCode65.isNumber2("e3"));
        System.out.println(" 6e-1" + "=>\t" + leetCode65.isNumber2(" 6e-1"));
        System.out.println(" 99e2.5 " + "=>\t" + leetCode65.isNumber2(" 99e2.5 "));
        System.out.println("53.5e93" + "=>\t" + leetCode65.isNumber2("53.5e93"));
        System.out.println(" --6 " + "=>\t" + leetCode65.isNumber2(" --6 "));
        System.out.println("-+3" + "=>\t" + leetCode65.isNumber2("-+3"));
        System.out.println("95a54e53" + "=>\t" + leetCode65.isNumber2("95a54e53"));
        System.out.println(" .1e3" + "=>\t" + leetCode65.isNumber2(" .1e3"));
        System.out.println(" 3." + "=>\t" + leetCode65.isNumber2(" 3."));
        System.out.println("1e.3" + "=>\t" + leetCode65.isNumber2("1e.3"));
        System.out.println("1e3." + "=>\t" + leetCode65.isNumber2("1e3."));
        System.out.println(".e1" + "=>\t" + leetCode65.isNumber2(".e1"));
        System.out.println("-1." + "=>\t" + leetCode65.isNumber2("-1."));
        System.out.println("46.e3" + "=>\t" + leetCode65.isNumber2("46.e3"));
        System.out.println("e1" + "=>\t" + leetCode65.isNumber2("e1"));
        System.out.println("0.." + "=>\t" + leetCode65.isNumber2("0.."));
        System.out.println("7e69e" + "=>\t" + leetCode65.isNumber2("7e69e"));
    }

}
