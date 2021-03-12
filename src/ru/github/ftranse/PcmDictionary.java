package ru.github.ftranse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static ru.github.ftranse.Personality.*;

public class PcmDictionary {

    private Map<String, Personality> dictionary;

    public PcmDictionary() {
        dictionary = new TreeMap<>();

        loadFileByPersonality(IMAGINER, "c:/work/PCM Analyzer/data/IMAGINER.txt");
        loadFileByPersonality(HARMONISER, "c:/work/PCM Analyzer/data/HARMONISER.txt");
        loadFileByPersonality(THINKER, "c:/work/PCM Analyzer/data/THINKER.txt");
        loadFileByPersonality(PROMOTER, "c:/work/PCM Analyzer/data/PROMOTER.txt");
        loadFileByPersonality(PERSISTER, "c:/work/PCM Analyzer/data/PERSISTER.txt");
        loadFileByPersonality(REBEL, "c:/work/PCM Analyzer/data/REBEL.txt");
//        System.out.println(dictionary);
    }

    public void addPcmWord(String word, Personality pers) {
        dictionary.put(word, pers);
    }

    public Map<String, Personality> getDictionary() {
        return dictionary;
    }

    private void loadFileByPersonality(Personality pers, String filePath) {

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(word -> addPcmWord(word, pers));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry entry : dictionary.entrySet()) {
            s.append(entry.getKey() + ": "  + entry.getValue() + "\n");
        }
        return s.toString();
    }
}
