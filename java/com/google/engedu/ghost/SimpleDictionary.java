package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        String word;

        if(prefix.isEmpty()){
            word=words.get((int)Math.random());
            return word;
        }

            return search(prefix);
    }

    public String search(String prefix){
        int low,high,mid;
        low=0;
        high=words.size()-1;
        mid=(low+high)/2;
        String midword=words.get(mid);

        while(low<=high){

            mid=(low+high)/2;
            if(midword.startsWith(prefix))
            {
                return  midword;
            }

            if(midword.compareTo(prefix)<0)
            {
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return null;

    }
    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
