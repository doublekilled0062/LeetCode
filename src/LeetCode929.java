import java.util.HashSet;
import java.util.Set;

/**
 * 929. 独特的电子邮件地址
 *
 * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
 * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
 * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
 * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。
 * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
 * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。 （同样，此规则不适用于域名。）
 * 可以同时使用这两个规则。
 * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
 *
 * 示例：
 * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 * 提示：
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
 */
public class LeetCode929 {
    public int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(int i = 0; i < emails.length; i++){
            String[] strs = emails[i].split("@");
            int index = strs[0].indexOf("+");
            if(index > 0){
                strs[0] = strs[0].substring(0, index);
            }
            strs[0] = strs[0].replaceAll("\\.", "");
            set.add(strs[0] + "@" + strs[1]);
        }
        return set.size();
    }

    /**
     * 总感觉上面的实现比较low
     * 测了一下果然快
     * @param emails
     * @return
     */
    public int numUniqueEmails2(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(int i = 0; i < emails.length; i++){
            int index = 0;
            int cur = 0;
            char[] chars = emails[i].toCharArray();
            boolean atFlag = false;
            while (cur < chars.length){
                if(chars[cur] == '.'){
                    if(!atFlag){
                        //没有走到@ 略过这个字符
                        cur++;
                        continue;
                    }else {
                        //照常
                        chars[index++] = chars[cur++];
                    }
                }else if(chars[cur] == '+'){
                    while (chars[cur] != '@'){
                        cur++;
                    }
                }else if(chars[cur] == '@'){
                    atFlag = true;
                    chars[index++] = chars[cur++];
                }else {
                    //照常
                    chars[index++] = chars[cur++];
                }
            }
            set.add(new String(chars, 0, index));
        }
        return set.size();
    }

    public static void main(String[] args) {
        LeetCode929 leetCode929 = new LeetCode929();
        System.out.println(leetCode929.numUniqueEmails2(new String[]{"testemail@leetcode.com","testemail1@leetcode.com","testemail+david@lee.tcode.com"}));
    }
}
