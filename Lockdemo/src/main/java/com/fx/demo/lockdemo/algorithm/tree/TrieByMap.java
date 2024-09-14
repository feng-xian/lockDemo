package com.fx.demo.lockdemo.algorithm.tree;

import java.util.HashMap;

/**
 * 通过map实现前缀树
 */
public class TrieByMap {

    private NodeMap prefixTree;

    public TrieByMap() {
        prefixTree = new NodeMap();
    }

    public static void main(String[] args) {
        String word = "and";

        TrieByMap trie = new TrieByMap();
        trie.insert(word);

        System.out.println("------------------");
        System.out.println(trie.search(word));
        System.out.println(trie.search("word"));
        System.out.println(trie.prefix("an"));
        System.out.println(trie.prefix("a"));
        System.out.println(trie.delete("at"));
        System.out.println(trie.delete(word));

    }


    public boolean delete(String word) {
        if (null == word || word.length() == 0) {
            return false;
        }

        if (!search(word)) {
            return false;
        }

        char[] array = word.toCharArray();

        NodeMap node = prefixTree;

        for (int i = 0; i < array.length; i++) {
            int index = array[i] - 'a';

            node.next.get(index).pass--;

            int indexNext = i + 1;
            if (indexNext < array.length) {
                int np = array[indexNext] - 'a';
                if (node.next.get(index).next.get(np) == null
                        || node.next.get(index).next.get(np).pass == 1) {
                    node.next.remove(index);
                    return true;
                }

            }


            if (i == array.length - 1) {
                node.next.get(index).end--;
            }

            node = node.next.get(index);

        }

        return true;
    }

    public boolean prefix(String word) {
        if (null == word || word.length() == 0) {
            return false;
        }

        char[] array = word.toCharArray();

        NodeMap node = prefixTree;

        for (int i = 0; i < array.length; i++) {
            int index = array[i] - 'a';

            if ((null == node.next.get(index)) || (0 == node.next.get(index).pass)) {
                return false;
            }
        }

        return true;
    }

    public boolean search(String word) {
        if (null == word || word.length() == 0) {
            return false;
        }

        char[] array = word.toCharArray();

        NodeMap node = prefixTree;

        for (int i = 0; i < array.length; i++) {

            int index = array[i] - 'a';

            if ((node.next.get(index) == null) || node.next.get(index).pass == 0) {
                return false;
            }
            if ((i == array.length - 1) && node.next.get(index).end == 0) {
                return false;
            }
            node = node.next.get(index);
        }

        return true;
    }

    public void insert(String word) {
        if (null == word || word.length() == 0) {
            return;
        }

        char[] array = word.toCharArray();

        NodeMap node = prefixTree;
        node.pass++;

        for (int i = 0; i < array.length; i++) {

            int index = array[i] - 'a';
            if (node.next.get(index) == null) {
                node.next.put(index, new NodeMap());
            }
            node.next.get(index).pass++;

            if (i == array.length - 1) {
                node.next.get(index).end++;
            }

            node = node.next.get(index);

        }

    }


    public class NodeMap {
        public int pass;
        public int end;

        public HashMap<Integer, NodeMap> next;

        public NodeMap() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

}
