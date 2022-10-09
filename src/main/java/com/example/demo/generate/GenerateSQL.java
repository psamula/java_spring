package com.example.demo.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GenerateSQL {
    public static void generateSQLFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/data.sql", false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("");

        for (int i = 1; i <= 100; i++) {
            String s = String.format("insert into post(id, title, content, created) values (%d, 'Test post %d', 'Content %d', '%s');\n", i, i, i, LocalDateTime.now().minusDays(100 - i));
            bw.append(s);
        }

        for (int i = 1; i < 100; i++) {
            int postidd = 1 + i / 10;
            String w = String.format("insert into comment(id, post_id, content, created) values (%d, %d, 'Content com %d', '%s');\n", i, postidd, i, LocalDateTime.now().minusDays(100 - i));
            bw.append(w);
        }

        bw.close();
    }
}
