package com.fx.demo.lockdemo.algorithm.tree;

/**
 * 通过数组实现前缀树
 */
public class TrieByArr {

    private NodeArr prefixTree;

    public class NodeArr {
        public int end;
        public int pass;

        public NodeArr[] path;

        public NodeArr() {
            this.end = 0;
            this.pass = 0;
            path = new NodeArr[26];
        }
    }

    public TrieByArr() {
        prefixTree = new NodeArr();
    }

    public void insert(String word) {
        if (null == word || word.length() == 0) {
            return;
        }

        char[] charArray = word.toCharArray();
        NodeArr root = prefixTree;
        root.pass++;

        for (int i = 0; i < charArray.length; i++) {

            char ch = charArray[i];
            int index = ch - 'a';
            if (null == root.path[index]) {
                root.path[index] = new NodeArr();
            }
            root.path[index].pass += 1;

            if (i == charArray.length - 1) {
                root.path[index].end += 1;
            }
            root = root.path[index];
        }
    }

    /**
     * 检测指定字符串是否存在
     *
     * @param word
     * @return
     */
    public int search(String word) {

        if (null == word || word.length() == 0) {
            return 0;
        }

        char[] charArray = word.toCharArray();
        NodeArr root = prefixTree;

        for (int i = 0; i < charArray.length; i++) {
            int index = charArray[i] - 'a';

            if (null == root.path[index] || root.path[index].pass == 0) {
                return 0;
            }

            if ((i == charArray.length - 1) && (root.path[index].end == 0)) {
                return 0;
            }

            root = root.path[index];
        }

        return 1;
    }

    /**
     * 检测是否由指定字符串开头
     *
     * @param word
     * @return
     */
    public int prefix(String word) {

        if (null == word || word.length() == 0) {
            return 0;
        }

        char[] charArray = word.toCharArray();

        NodeArr root = prefixTree;
        for (int i = 0; i < charArray.length; i++) {

            int index = charArray[i] - 'a';

            if ((root.path[index] == null) || (root.path[index].pass == 0)) {
                return 0;
            }

            root = root.path[index];
        }

        return 1;
    }

    /**
     * 从前缀树上删除指定字符串
     * @param word
     * @return 0 删除失败。1删除成功
     */
    public int delete(String word) {
        if (null == word || word.length() == 0) {
            return 0;
        }

        if (search(word) == 0) {
            return 0;
        }

        char[] charArray = word.toCharArray();

        NodeArr root = prefixTree;
        for (int i = 0; i < charArray.length; i++) {
            int index = charArray[i] - 'a';
            if (null == root.path[index]) {
                return 1;
            }

            root.path[index].pass -= 1;

            int nextP = i + 1;
            if (nextP < charArray.length) {
                int indexN = charArray[nextP] - 'a';
                if (root.path[index].path[indexN].pass == 1) {
                    root.path[index] = null;
                    return 1;
                }
            }

            root = root.path[index];
            if (i == charArray.length - 1) {
                root.path[index].end -= 1;
            }

        }

        return 1;
    }

    public static void main(String[] args) {
        String word = "and";

        TrieByArr trieByArr = new TrieByArr();
        trieByArr.insert(word);

        System.out.println("------------------");
        System.out.println(trieByArr.search(word));
        System.out.println(trieByArr.search("word"));
        System.out.println(trieByArr.prefix("an"));
        System.out.println(trieByArr.prefix("a"));
        System.out.println(trieByArr.delete("at"));
        System.out.println(trieByArr.delete(word));

    }


}
