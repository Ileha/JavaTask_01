package app.LogFile;

import app.SubstringFinder.SubstringNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FindExpression {
    FileNode Action(File file) throws SubstringNotFound, IOException;
}
