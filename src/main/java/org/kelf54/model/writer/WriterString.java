package org.kelf54.model.writer;

import java.io.IOException;

public class WriterString extends WriterData {
    public WriterString(String patch, boolean isAppendToFile) throws IOException {
        super(patch, "string", isAppendToFile);
    }
}
