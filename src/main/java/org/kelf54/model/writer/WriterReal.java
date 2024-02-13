package org.kelf54.model.writer;

import java.io.IOException;

public class WriterReal extends WriterData {
    public WriterReal(String patch, boolean isAppendToFile) throws IOException {
        super(patch, "float", isAppendToFile);
    }
}
