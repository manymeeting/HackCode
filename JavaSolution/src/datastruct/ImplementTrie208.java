package datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 *Implement a trie with insert, search, and startsWith methods.

 Example:

 Trie trie = new Trie();

 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true
 Note:

 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.
 * */

// 每个节点包括一个Map<Integer, TrieNode>用来存所有的children（Integer是当前字符-'a'的值），
// 同时维护一个isWord的boolean变量来判断是否是完整word

public class ImplementTrie208 {

    /** Initialize your data structure here. */
    class TrieNode {
        Map<Integer, TrieNode> children;
        boolean isWord;

        TrieNode() {
            children = new HashMap<>();
        }
    }

    private TrieNode root;

    public ImplementTrie208() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int key = ch - 'a';
            if(!curr.children.containsKey(key)) {
                // create new child node if doesn't exsit
                curr.children.put(key, new TrieNode());
            }

            curr = curr.children.get(key);
        }
        // 最后的位置标记为word
        curr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int key = ch - 'a';
            if(!curr.children.containsKey(key)) {
                return false;
            }
            curr = curr.children.get(key);
        }
        return curr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            int key = ch - 'a';
            if(!curr.children.containsKey(key)) {
                return false;
            }
            curr = curr.children.get(key);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */