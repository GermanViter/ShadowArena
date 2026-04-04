package com.rpg.core;

public class ConsoleUtils {
    /**
     * Prints a message letter by letter with a typewriter effect.
     * It detects ANSI color codes and prints them instantly to avoid flickering.
     */
    public static void slowPrint(String message) {
        boolean inEscape = false;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            
            // Detect start of ANSI escape sequence
            if (c == '\033') {
                inEscape = true;
            }
            
            System.out.print(c);
            
            // If we are inside an escape sequence, don't sleep
            if (inEscape) {
                if (c == 'm') {
                    inEscape = false;
                }
                continue; 
            }
            
            // Small delay for the typewriter effect
            try {
                Thread.sleep(15); // 15ms is usually the "sweet spot" for readability
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); // Add the newline at the end
    }

    /**
     * Same as slowPrint but without the final newline (useful for prompts)
     */
    public static void slowPrintNoLine(String message) {
        boolean inEscape = false;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == '\033') inEscape = true;
            System.out.print(c);
            if (inEscape) {
                if (c == 'm') inEscape = false;
                continue; 
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
