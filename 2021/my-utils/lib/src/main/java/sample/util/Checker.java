package sample.util;

public abstract class Checker {

    /** 拡張子：.csvの時はtrue */
    public static boolean isCsv(String fileName) {
        return chkFileExtension(fileName, "csv");
    }

    /** 拡張子：.csv.gzの時はtrue */
    public static boolean isCsvGz(String fileName) {
        return chkFileExtension(fileName, "csv", "gz");
    }

    /** 拡張子チェック。(nullは許容しない) */
    public static boolean chkFileExtension(String fileName, String fileExtension) {
        return fileName != null ? fileName.matches("^.*\\." + fileExtension + "$") : false;
    }

    /** 拡張子チェック。(nullは許容しない) */
    public static boolean chkFileExtension(String fileName, String fileExtension, String fileExtension1) {
        return fileName != null ? fileName.matches("^.*\\." + fileExtension + "\\." + fileExtension1 + "$") : false;
    }
}
