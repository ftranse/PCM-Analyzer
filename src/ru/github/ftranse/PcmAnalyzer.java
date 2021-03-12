package ru.github.ftranse;

import java.io.*;
import java.util.*;

import static ru.github.ftranse.Personality.*;

public class PcmAnalyzer {

    private final PcmDictionary dictionary;
    private final Map<Personality, Long> pcmMap;
    private ArrayList<String> words;

    public PcmAnalyzer() {
        pcmMap = new HashMap<>();

        pcmMap.put(IMAGINER, 0L);
        pcmMap.put(HARMONISER, 0L);
        pcmMap.put(PERSISTER, 0L);
        pcmMap.put(PROMOTER, 0L);
        pcmMap.put(REBEL, 0L);
        pcmMap.put(THINKER, 0L);
        pcmMap.put(NOT_PCM, 0L);

        dictionary = new PcmDictionary();
    }

    public void loadAndNormalize(String file) {
        words = loadWords(file);

        for(String entry : words) {
            if (dictionary.getDictionary().containsKey(entry)) {
                Personality type = dictionary.getDictionary().get(entry);
                pcmMap.put(type, pcmMap.get(type) + 1);

//                System.out.println("Word: " + entry + ", " + type);
            } else {
                pcmMap.put(NOT_PCM, pcmMap.get(NOT_PCM) + 1);

            }
//            pcmMap.computeIfAbsent(type, (k, v) -> v + 1);
        }
    }

    private ArrayList<String> loadWords(String file) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String strLine;
        ArrayList<String> words = new ArrayList<>();
        try {
            while ((strLine = reader.readLine()) != null) {
                String[] tmp = strLine.toLowerCase().split("[1234567890.,:;!*\\s\\n\\t]+");
                words.addAll(Arrays.asList(tmp));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n<<<< PCM Words Count >>>>\n");

        Long sum = 0L;
        for (Map.Entry entry : pcmMap.entrySet()) {
            Personality key = (Personality) entry.getKey();
            Long value = (Long) entry.getValue();
            s.append(key + ":\t\t\t"  + value + "\n");

            if(key != NOT_PCM) {
                sum += value;
            }
        }

        s.append("\n<<<< PCM Ranking >>>>\n");
 //       DecimalFormat formatter = new DecimalFormat("#0.00");
        for (Map.Entry entry : pcmMap.entrySet()) {
            Personality key = (Personality) entry.getKey();
            Long value = (Long) entry.getValue();

            if(key != NOT_PCM) {
                s.append(key + ":\t\t\t" + 100*value/sum + "%\n");
            }
        }
        return s.toString();
    }
}
