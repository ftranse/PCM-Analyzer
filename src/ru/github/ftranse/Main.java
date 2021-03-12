package ru.github.ftranse;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        PcmAnalyzer pcm = new PcmAnalyzer();
        pcm.loadAndNormalize("c:/work/PCM Analyzer/data/input2.txt");
        System.out.println(pcm);
    }
}
