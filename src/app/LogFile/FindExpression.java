package app.LogFile;

import app.SubstringFinder.SubstringNotFound;

import java.io.File;
import java.io.FileNotFoundException;

public interface FindExpression {
    FileNode Action(File file) throws SubstringNotFound, FileNotFoundException;
}
