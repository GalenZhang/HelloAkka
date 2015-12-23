package com.achievo.akka.message;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class WordCount {
    private String word;

    private Integer count;

    public WordCount(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
