package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
// COMMENTS: the hints of building this class come from
//           https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
