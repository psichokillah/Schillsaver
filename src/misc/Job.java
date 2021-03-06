package misc;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Job {
    /** The unique id of the Job. */
    @Getter @Setter private int id;
    /** The name of the Job. */
    @Getter private String name;
    /** A rough description of the Job. */
    @Getter private String description;
    /** The directory in which to place the output file(s). */
    @Getter private String outputDirectory;
    /** The file(s) belonging to the Job.*/
    @Getter private List<File> files;
    /** Whether or not the Job is an Encode Job. If not, then it's a Decode Job. */
    @Getter private boolean isEncodeJob;

    /** Whether or not to pack all of the files into a single archive before encoding. */
    @Getter private boolean archiveFiles = false;

    /**
     * Constructs a new Job.
     *
     * @param name
     *         The name of the Job.
     *
     * @param description
     *         A rough description of the Job.
     *
     * @param outputDirectory
     *         The directory in which to place the output file(s).
     *
     * @param files
     *         The file(s) belonging to the Job.
     *
     * @param isEncodeJob
     *         Whether or not the Job is an Encode Job. If not, then it's a Decode Job.
     *
     * @param archiveFiles
     *         Whether or not to pack all of the files into a single archive before encoding.
     */
    public Job(final String name, final String description, final String outputDirectory, final List<File> files, final boolean isEncodeJob, final boolean archiveFiles) {
        this.name = name;
        this.description = description;

        if (outputDirectory.endsWith("\\") || outputDirectory.endsWith("/")) {
            this.outputDirectory = outputDirectory;
        } else {
            this.outputDirectory = outputDirectory + "/";
        }

        this.files = files;
        this.isEncodeJob = isEncodeJob;
        this.archiveFiles = archiveFiles;


        // Sort the files from smallest to largest:
        final Comparator<File> comparator = Comparator.comparing(File::length);
        Collections.sort(this.files, comparator);
    }

    /** @return The full designation of the Job. This includes the unique ID, type, and name. */
    public String getFullDesignation() {
        return id + " - " + (isEncodeJob ? "Encode" : "Decode") + " - " + name;
    }
}
